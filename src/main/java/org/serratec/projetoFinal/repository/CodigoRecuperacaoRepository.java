package org.serratec.projetoFinal.repository;

import org.serratec.projetoFinal.domain.CodigoRecuperacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodigoRecuperacaoRepository extends JpaRepository<CodigoRecuperacao, Long> {
	
	CodigoRecuperacao findByCodigo(String codigo);
}
