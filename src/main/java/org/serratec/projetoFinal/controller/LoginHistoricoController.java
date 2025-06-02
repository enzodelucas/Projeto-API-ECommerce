package org.serratec.projetoFinal.controller;

import java.security.Principal;
import java.util.List;

import org.serratec.projetoFinal.domain.LoginHistorico;
import org.serratec.projetoFinal.service.LoginHistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login/historico")
public class LoginHistoricoController {

	@Autowired
    private LoginHistoricoService loginHistoricoService;

    public LoginHistoricoController(LoginHistoricoService loginHistoricoService) {
        this.loginHistoricoService = loginHistoricoService;
    }

    @GetMapping
    public List<LoginHistorico> historicoPegar(Principal principal) {
        return loginHistoricoService.historicoPuxa(principal.getName()); // Aqui também é o email
    }
}