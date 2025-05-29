package org.serratec.projetoFinal.repository;

import org.serratec.projetoFinal.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	Cliente findByEmail(String email);
	Cliente findByCpf(String cpf);
}
