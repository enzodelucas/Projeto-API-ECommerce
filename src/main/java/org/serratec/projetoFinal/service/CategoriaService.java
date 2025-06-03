package org.serratec.projetoFinal.service;

import java.util.List;
import java.util.Optional;

import org.serratec.projetoFinal.domain.Categoria;
import org.serratec.projetoFinal.exception.CategoriaException;
import org.serratec.projetoFinal.exception.NaoEncontradoException;
import org.serratec.projetoFinal.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria inserir(Categoria categoria) throws CategoriaException {
		if (categoriaRepository.findByNomeIgnoreCase(categoria.getNome()) != null) {
			throw new CategoriaException("Esta categoria já está cadastrada.");
		}
		return categoriaRepository.save(categoria);
	}

	public List<Categoria> listar() {
		List<Categoria> categorias = categoriaRepository.findAll();
		return categorias;
	}

	public Categoria editar(Long id, Categoria categoria) throws CategoriaException{
		Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
		if (categoriaOpt.isPresent()) {
			Categoria c = categoriaOpt.get();
			categoria.setId(id);
			c.setNome(categoria.getNome());
			return categoriaRepository.save(c);
		}
		
		throw new NaoEncontradoException("Categoria não foi encontrada");
	}
	
	public Categoria buscarCategoria(Long id) {
		Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
		if (categoriaOpt.isPresent()) {
			Categoria categoria = categoriaOpt.get();
			return categoria;
		}
		throw new NaoEncontradoException("Categoria não foi encontrada");
		
	}
}
