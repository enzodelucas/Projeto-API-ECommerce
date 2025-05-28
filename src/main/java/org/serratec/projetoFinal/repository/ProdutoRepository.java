package org.serratec.projetoFinal.repository;

import org.serratec.projetoFinal.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository  extends JpaRepository<Produto, Long>{

}
