package org.serratec.projetoFinal.repository;

import java.util.List;

import org.serratec.projetoFinal.domain.LoginHistorico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginHistoricoRepository extends JpaRepository<LoginHistorico, Long> {
    List<LoginHistorico> findByEmail(String email);
}