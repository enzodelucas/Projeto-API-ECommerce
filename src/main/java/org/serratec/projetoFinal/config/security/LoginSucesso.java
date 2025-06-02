package org.serratec.projetoFinal.config.security;

import org.serratec.projetoFinal.service.LoginHistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class LoginSucesso implements ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired
    private LoginHistoricoService loginHistoricoService;

    public LoginSucesso(LoginHistoricoService loginHistoricoService) {
        this.loginHistoricoService = loginHistoricoService;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String email = event.getAuthentication().getName(); // Aqui é o email do usuário

        loginHistoricoService.registrarLogin(email);
    }
}