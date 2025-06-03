package org.serratec.projetoFinal.repository;

import java.util.List;

import org.serratec.projetoFinal.domain.Cliente;
import org.serratec.projetoFinal.domain.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {

    List<Favorito> findByCliente(Cliente cliente);

    boolean existsByClienteAndProdutoId(Cliente cliente, Long produtoId);

    void deleteByClienteAndProdutoId(Cliente cliente, Long produtoId);
}
