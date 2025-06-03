package org.serratec.projetoFinal.repository;

import java.util.List;

import org.serratec.projetoFinal.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository  extends JpaRepository<Produto, Long> {
	
	List<Produto> findByValorBetween(Double valorMinimo, Double valorMaximo);
	
	List<Produto> findByNomeContainingIgnoreCase(String nome);

}
