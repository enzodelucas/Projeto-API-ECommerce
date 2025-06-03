package org.serratec.projetoFinal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.projetoFinal.config.MailConfig;
import org.serratec.projetoFinal.domain.Cliente;
import org.serratec.projetoFinal.domain.ClienteEndereco;
import org.serratec.projetoFinal.domain.Pedido;
import org.serratec.projetoFinal.domain.Produto;
import org.serratec.projetoFinal.domain.ProdutoPedido;
import org.serratec.projetoFinal.dto.PedidoAtualizarStatusDTO;
import org.serratec.projetoFinal.dto.PedidoDTO;
import org.serratec.projetoFinal.dto.PedidoInserirDTO;
import org.serratec.projetoFinal.dto.ProdutoInserir;
import org.serratec.projetoFinal.enuns.FormaPgto;
import org.serratec.projetoFinal.enuns.Status;
import org.serratec.projetoFinal.exception.NaoEncontradoException;
import org.serratec.projetoFinal.exception.PedidoNaoPodeSerAlteradoException;
import org.serratec.projetoFinal.exception.UsuarioNaoPermitidoException;
import org.serratec.projetoFinal.repository.ClienteEnderecoRepository;
import org.serratec.projetoFinal.repository.ClienteRepository;
import org.serratec.projetoFinal.repository.PedidoRepository;
import org.serratec.projetoFinal.repository.ProdutoPedidoRepository;
import org.serratec.projetoFinal.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	MailConfig mailConfig;

	@Autowired
	AutenticacaoService autenticacaoService;

	@Autowired
	ClienteEnderecoRepository clienteEndereco;

	@Autowired
	ProdutoPedidoRepository produtoPedidoRepository;

	@Autowired
	ProdutoService produtoService;



	public PedidoDTO inserirPedido(PedidoInserirDTO pedidoIns) {
		if(produtoService.verificarEstoque(pedidoIns)) {

			Cliente cliente = autenticacaoService.clienteAutenticacao();
			Pedido pedido = new Pedido();
			pedido.setCliente(cliente);

			Optional<ClienteEndereco> enderecoOpt = clienteEndereco.findById(pedidoIns.getIdEndereco());
			if(enderecoOpt.isPresent()){
				ClienteEndereco enderecoEntrega = enderecoOpt.get();
				if (!cliente.getEnderecos().contains(enderecoEntrega)) {
					throw new UsuarioNaoPermitidoException("Endereço não pertence ao cliente autenticado");
				} 
				pedido.setEnderecoEntrega(enderecoEntrega);
			} else {
				throw new NaoEncontradoException("Endereço não encontrado");
			}

			double valorTotal = 0.0;

			List<ProdutoPedido> produtos = new ArrayList<>();


			for (ProdutoInserir produtoInserir : pedidoIns.getProduto()) {
				Optional<Produto> produto = produtoRepository.findById(produtoInserir.getId());
				if (produto.isEmpty()) {
					throw new NaoEncontradoException("ID " + produtoInserir.getId() + " não vinculado a nenhum produto.");
				}
				Produto produtoEncontrado = produto.get();

				ProdutoPedido porProduto = new ProdutoPedido();

				porProduto.setProduto(produtoEncontrado);
				porProduto.setQuantidade(produtoInserir.getQuantidade());
				porProduto.setValor(produtoEncontrado.getValor() * porProduto.getQuantidade());
				porProduto.setPedido(pedido);

				valorTotal += porProduto.getValor();
				produtos.add(porProduto);

			}

			pedido.setFormaPgto(pedidoIns.getFormaPgto());
			if (pedido.getFormaPgto() == FormaPgto.PIX) {
				Double desconto = valorTotal * 0.10;
				pedido.setDesconto(desconto);
				valorTotal -= desconto;
			}
			pedido.setProdutos(produtos);
			pedido.setValorFinal(valorTotal);


			pedidoRepository.save(pedido);
			produtoPedidoRepository.saveAll(produtos);

			PedidoDTO pedidoDTO = new PedidoDTO(pedido);

			return pedidoDTO;
		} else {
			throw new NaoEncontradoException("Quantidade de produto escolhida não condiz com o estoque.");
		}
	}



	public PedidoDTO pedidoCancelado(Long id) {
		Cliente cliente = autenticacaoService.clienteAutenticacao();
		Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
		if(pedidoOpt.isPresent()) {
			Pedido pedido = pedidoOpt.get();
			if (!cliente.getPedidos().contains(pedido)) {
				throw new UsuarioNaoPermitidoException("Pedido não pertence ao cliente autenticado");
			}
			if(pedido.getStatus() == Status.ENTREGUE) {
				throw new PedidoNaoPodeSerAlteradoException("Não é possível cancelar um pedido que já foi entregue.");
			}
			pedido.setStatus(Status.CANCELADO);
			pedidoRepository.save(pedido);
			PedidoDTO pedidoDTO = new PedidoDTO(pedido);

			mailConfig.sendEmailAttStatus(pedido.getCliente().getEmail(), "Atualização no status do pedido", pedido.toString());

			return pedidoDTO;
		}

		throw new NaoEncontradoException("ID não vinculado a nenhum pedido");

	}

	public PedidoDTO pedidoEntregue(Long id) {
		Cliente cliente = autenticacaoService.clienteAutenticacao();
		Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
		if(pedidoOpt.isPresent()) {
			Pedido pedido = pedidoOpt.get();
			if (!cliente.getPedidos().contains(pedido)) {
				throw new UsuarioNaoPermitidoException("Pedido não pertence ao cliente autenticado");
			}
			if(pedido.getStatus() == Status.CANCELADO) {
				throw new PedidoNaoPodeSerAlteradoException("Não é possível marcar como entregue um pedido que já foi cancelado.");
			}
			pedido.setStatus(Status.ENTREGUE);
			pedidoRepository.save(pedido);
			PedidoDTO pedidoDTO = new PedidoDTO(pedido);

			mailConfig.sendEmailAttStatus(pedido.getCliente().getEmail(), "Atualização no status do pedido", pedido.toString());

			return pedidoDTO;
		}

		throw new NaoEncontradoException("ID não vinculado a nenhum pedido");

	}

	public PedidoDTO listarPedidosId(Long id) {
		Cliente cliente = autenticacaoService.clienteAutenticacao();
		List<Pedido> pedidos = cliente.getPedidos();
		Optional<Pedido> pedidosOpt = pedidoRepository.findById(id);
		if(pedidosOpt.isPresent()) {
			Pedido pedidoId = pedidosOpt.get();
			PedidoDTO pedidoDTO = new PedidoDTO(pedidoId);
			return pedidoDTO;
		} else {
			throw new NaoEncontradoException("Pedido não encontrado");
		}
	}

	public List<PedidoDTO> listaTodosPedidos(){
		List<Pedido> pedidos = pedidoRepository.findAll();
		List<PedidoDTO> pedidoDTO = new ArrayList<>();
		for(Pedido pedido : pedidos) {
			pedidoDTO.add(new PedidoDTO(pedido));
		}
		return pedidoDTO;
	}

	public PedidoDTO atualizarStatusPedido(Long id, PedidoAtualizarStatusDTO pedidoAtt) { // para funcionarios
		Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
		if(pedidoOpt.isPresent()) {
			Pedido pedido = pedidoOpt.get();
			if(pedido.getStatus() == Status.CANCELADO || pedido.getStatus() == Status.ENTREGUE) {
				throw new PedidoNaoPodeSerAlteradoException("Não é possível alterar o status de um pedido já cancelado ou entregue.");
			}
			pedido.setStatus(pedidoAtt.getStatus());
			pedidoRepository.save(pedido);
			PedidoDTO pedidoDTO = new PedidoDTO(pedido);
			
			mailConfig.sendEmailAttStatus(pedido.getCliente().getEmail(), "Atualização no status do pedido", pedido.toString());
			
			return pedidoDTO;
		}else {
			throw new NaoEncontradoException("Pedido não encontrado");
		}
	}
}
