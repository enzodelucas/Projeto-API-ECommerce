package org.serratec.projetoFinal.repository;

import org.serratec.projetoFinal.domain.ProdutoPedido;
import org.serratec.projetoFinal.domain.ProdutoPedidoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedido, ProdutoPedidoPK> {

}
