package org.serratec.projetoFinal.service;

import java.time.LocalDateTime;
import java.util.List;

import org.serratec.projetoFinal.domain.LoginHistorico;
import org.serratec.projetoFinal.repository.LoginHistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginHistoricoService {

	@Autowired
    private LoginHistoricoRepository loginHistoricoRepository;

    public LoginHistoricoService(LoginHistoricoRepository loginHistoricoRepository) {
        this.loginHistoricoRepository = loginHistoricoRepository;
    }

    public void registrarLogin(String email) {
        LoginHistorico historico = new LoginHistorico();
        historico.setEmail(email);
        historico.setHoraDoLogin(LocalDateTime.now());
        loginHistoricoRepository.save(historico);
    }

    public List<LoginHistorico> historicoPuxa(String email) {
        return loginHistoricoRepository.findByEmail(email);
    }
}