package org.serratec.projetoFinal.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.projetoFinal.domain.Categoria;
import org.serratec.projetoFinal.domain.Produto;
import org.serratec.projetoFinal.domain.ProdutoPedido;
import org.serratec.projetoFinal.dto.PedidoInserirDTO;
import org.serratec.projetoFinal.dto.ProdutoDTO;
import org.serratec.projetoFinal.dto.ProdutoInserir;
import org.serratec.projetoFinal.dto.ProdutoInserirDTO;
import org.serratec.projetoFinal.exception.CategoriaException;
import org.serratec.projetoFinal.exception.NaoEncontradoException;
import org.serratec.projetoFinal.repository.CategoriaRepository;
import org.serratec.projetoFinal.repository.ClienteRepository;
import org.serratec.projetoFinal.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ClienteRepository clienteRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	CategoriaRepository categoriaRepository;

    ProdutoService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

	public ProdutoDTO inserir(ProdutoInserirDTO produtoIns) throws CategoriaException {
		Categoria categoria = categoriaRepository.findByNomeIgnoreCase(produtoIns.getCategoria());
		if (categoria == null) {
			throw new CategoriaException("Categoria inválida");
		}
		Produto produto = new Produto(produtoIns);
		produto.setCategoria(categoria);

		categoria.getProdutos().add(produto);

		produtoRepository.save(produto);
		ProdutoDTO produtoDTO = new ProdutoDTO(produto);

		return produtoDTO;
	}

	public List<ProdutoDTO> listarTodos() {
		List<Produto> produtos = produtoRepository.findAll();
		List<ProdutoDTO> produtoDTO = new ArrayList<>();
		for (Produto produto : produtos) {
			produtoDTO.add(new ProdutoDTO(produto));
		}
		return produtoDTO;
	}
	
	public ProdutoDTO editar(Long id, ProdutoInserirDTO produtoIns) throws CategoriaException, NaoEncontradoException{
		Optional<Produto> produtoOpt = produtoRepository.findById(id);
		if (produtoOpt.isPresent()) {
			Produto p = produtoOpt.get();
			p.setId(id);
			p.setNome(produtoIns.getNome());
			p.setDescricao(produtoIns.getDescricao());
			Categoria categoria = categoriaRepository.findByNomeIgnoreCase(produtoIns.getCategoria());
			if (categoria == null) {
				throw new CategoriaException("Categoria inválida");
			}
			p.setCategoria(categoria);
			p.setValor(produtoIns.getValor());
			produtoRepository.save(p);
			
			ProdutoDTO produtoDTO = new ProdutoDTO(p);
			return produtoDTO;
		}
		
		throw new NaoEncontradoException("produto não foi encontrado");
	}
	
	public boolean verificarEstoque(PedidoInserirDTO pedidoIns) {
		List<ProdutoInserir> produtos = pedidoIns.getProduto();
		for(ProdutoInserir produtoIns : pedidoIns.getProduto()) {
			Long idProduto = produtoIns.getId();
			Integer quantProduto = produtoIns.getQuantidade();
			Optional<Produto> produtoOpt = produtoRepository.findById(idProduto);
			if(produtoOpt.isPresent()) {
				Produto produto = produtoOpt.get();
				if(produto.getEstoque()>quantProduto) {
					return true;
				}
			}
		}
		return false;
	}
}
