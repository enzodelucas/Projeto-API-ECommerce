package org.serratec.projetoFinal.service;



import java.util.ArrayList;
import java.util.List;

import org.serratec.projetoFinal.domain.Categoria;
import org.serratec.projetoFinal.domain.Cliente;
import org.serratec.projetoFinal.domain.Produto;
import org.serratec.projetoFinal.dto.ProdutoDTO;
import org.serratec.projetoFinal.dto.ProdutoDTO;
import org.serratec.projetoFinal.dto.ProdutoInserirDTO;
import org.serratec.projetoFinal.exception.CategoriaException;
import org.serratec.projetoFinal.repository.CategoriaRepository;
import org.serratec.projetoFinal.repository.ClienteRepository;
import org.serratec.projetoFinal.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
