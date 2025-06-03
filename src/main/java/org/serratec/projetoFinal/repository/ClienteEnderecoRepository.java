package org.serratec.projetoFinal.repository;

import java.util.List;
import java.util.Optional;

import org.serratec.projetoFinal.domain.Cliente;
import org.serratec.projetoFinal.domain.ClienteEndereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteEnderecoRepository extends JpaRepository<ClienteEndereco, Long>{
	Optional<ClienteEndereco> findFirstByClienteId(Long clienteId);
}
