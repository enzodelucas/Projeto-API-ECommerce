package org.serratec.projetoFinal.repository;

import org.serratec.projetoFinal.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	Categoria findByNomeIgnoreCase(String nome);
}
