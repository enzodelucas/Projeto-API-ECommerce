package org.serratec.projetoFinal.service;

import java.util.Optional;

import org.serratec.projetoFinal.domain.Pedido;
import org.serratec.projetoFinal.domain.ProdutoPedido;
import org.serratec.projetoFinal.dto.PedidoDTO;
import org.serratec.projetoFinal.dto.PedidoInserirDTO;
import org.serratec.projetoFinal.enuns.Status;
import org.serratec.projetoFinal.repository.ClienteRepository;
import org.serratec.projetoFinal.repository.PedidoRepository;
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
	
	public PedidoDTO inserirPedido(PedidoInserirDTO pedidoIns, Long id) {
		ProdutoPedido produtoPedido = new ProdutoPedido();
		return null;
	}
	
	public PedidoDTO pedidoCancelado(Long id) {
		Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
		if(pedidoOpt.isPresent()) {
			Pedido pedido = pedidoOpt.get();
			pedido.setStatus(Status.CANCELADO);
			 pedidoRepository.save(pedido);
			 PedidoDTO pedidoDTO = new PedidoDTO(pedido);
			 return pedidoDTO;
		}
		
		return null;
		
	}
	
	public PedidoDTO pedidoEntregue(Long id) {
		Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
		if(pedidoOpt.isPresent()) {
			Pedido pedido = pedidoOpt.get();
			pedido.setStatus(Status.ENTREGUE);
			 pedidoRepository.save(pedido);
			 PedidoDTO pedidoDTO = new PedidoDTO(pedido);
			 return pedidoDTO;
		}
		
		return null;
		
	}
	
	
}
