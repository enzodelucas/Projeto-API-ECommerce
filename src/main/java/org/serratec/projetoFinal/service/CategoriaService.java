package org.serratec.projetoFinal.service;



import java.util.List;

import org.serratec.projetoFinal.domain.Categoria;
import org.serratec.projetoFinal.exception.CategoriaException;
import org.serratec.projetoFinal.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	public Categoria inserir(Categoria categoria) throws CategoriaException {
		if(categoriaRepository.findByNome(categoria.getNome()) != null) {
			throw new CategoriaException("Esta categoria já está cadastrada.");
		}
		return categoriaRepository.save(categoria);
	}
	
	public List<Categoria> listar() {
		List<Categoria> categorias = categoriaRepository.findAll();
		return categorias;
	}
}
