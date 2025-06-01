package org.serratec.projetoFinal;

import org.serratec.projetoFinal.domain.Funcionario;
import org.serratec.projetoFinal.dto.FuncionarioInserirDTO;
import org.serratec.projetoFinal.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProjetoFinalApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ProjetoFinalApplication.class, args);
	}
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@Override
	public void run(String... args) throws Exception {
		if(funcionarioRepository.findByEmail("admEnzo@gmail.com") == null) {
			FuncionarioInserirDTO funcionarioIns = new FuncionarioInserirDTO
					("Enzo","22981874619","349.639.630-00","ADM","admEnzo@gmail.com","12345678","12345678");
			funcionarioIns.setSenha(encoder.encode(funcionarioIns.getSenha()));
			Funcionario funcionario = new Funcionario(funcionarioIns);
			funcionarioRepository.save(funcionario);
			System.out.println("funcionario criado");
		}
	}

}
