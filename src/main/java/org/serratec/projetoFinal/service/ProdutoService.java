package org.serratec.projetoFinal.service;

import org.serratec.projetoFinal.domain.Categoria;
import org.serratec.projetoFinal.domain.Produto;
import org.serratec.projetoFinal.dto.ProdutoInserirDTO;
import org.serratec.projetoFinal.exception.CategoriaException;
import org.serratec.projetoFinal.repository.CategoriaRepository;
import org.serratec.projetoFinal.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	CategoriaRepository categoriaRepository;

	public Produto inserir(ProdutoInserirDTO produtoIns) throws CategoriaException {
		Categoria categoria = categoriaRepository.findByNomeIgnoreCase(produtoIns.getCategoria());
		if (categoria == null) {
			throw new CategoriaException("Categoria inválida");
		}
		Produto produto = new Produto(produtoIns);
		produto.setCategoria(categoria);
		
		categoria.getProdutos().add(produto);
		
		return produtoRepository.save(produto);
		 

	}
}
