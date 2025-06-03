package org.serratec.projetoFinal.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

	@Value("${dominio.openapi.dev-url}")
	private String devUrl;
	
	@Value("${dominio.openapi.prod-url}")
	private String prodUrl;
	
	@Bean
	public OpenAPI myOpenAPI() {
		Server devServer = new Server();
		devServer.setUrl(devUrl);
		devServer.setDescription("URL do servidor de desenvolvimento");
		
		Server prodServer = new Server();
		prodServer.setUrl(devUrl);
		prodServer.setDescription("URL do servidor de produção");
		
		Contact contact = new Contact();
		contact.setEmail("contato@meudominio.com.br");
		contact.setName("Serratec");
		contact.setUrl("https://www.meudominio.com.br");
		
		License apachaLicense = new License().name("Apache License").url("https://www.apache.org/licenses/LICENSE/2.0");
		
		Info info = new Info()
				.title("E-commerce Grupo 03").version("1.0.0").contact(contact).description("API desenvolvida para projeto final da disciplina Desenvolvimento de API RESTful"
						+ " \nDesenvolvido por: Beatriz Pinheiro, Enzo Dias, José Netto, Rayca Thais e Rodrigo Schuab")
				.termsOfService("https://www.meudominio.com.br/termos").license(apachaLicense);
		
		return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
	}
}
