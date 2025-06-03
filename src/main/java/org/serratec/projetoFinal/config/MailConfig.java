package org.serratec.projetoFinal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail(String para, String assunto, String texto) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("lojagrupo03@gmail.com");
		message.setTo(para);
		message.setSubject(assunto);
		
		String textoE = """
				Dados:
				
				%s
				
				Atenciosamente,
				Equipe E-Commerce Grupo 03
				""".formatted(texto);
		
		message.setText(textoE);
		javaMailSender.send(message);
	}

	public void sendEmailAtt(String para, String assunto, String texto) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("lojagrupo03@gmail.com");
		message.setTo(para);
		message.setSubject(assunto);
		String textoE = """
				Olá!
				
				Atualização de cadastro concluída com sucesso!
				
				Novos dados:
				
				%s
				
				Atenciosamente,  
				Equipe E-Commerce Grupo 03
				""".formatted(texto);
		
		message.setText(textoE);
		javaMailSender.send(message);
	}

	public void sendCodigoRecuperacao(String para, String codigo) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("lojagrupo03@gmail.com");
		message.setTo(para);
		message.setSubject("Recuperação de Senha");
		String texto = """
				Olá!

				Recebemos uma solicitação para redefinir sua senha.

				Seu código de verificação é: %s

				Atenciosamente,  
				Equipe E-Commerce Grupo 03
				""".formatted(codigo);

		message.setText(texto);
		javaMailSender.send(message);
	}

	public void sendEmailAttStatus(String para, String assunto, String texto) {
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setFrom("lojagrupo03@gmail.com");
	    message.setTo(para);
	    message.setSubject(assunto);
	    String textoE = """
	        Status do pedido atualizado:

	        Dados do pedido:
	        %s

	        Atenciosamente,
	        E-COMMERCE GRUPO 03
	        """.formatted(texto);

	    message.setText(textoE);
	    javaMailSender.send(message);
	}
}