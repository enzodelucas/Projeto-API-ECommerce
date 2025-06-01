package org.serratec.projetoFinal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.projetoFinal.config.MailConfig;
import org.serratec.projetoFinal.domain.Cliente;
import org.serratec.projetoFinal.domain.Funcionario;
import org.serratec.projetoFinal.dto.ClienteDTO;
import org.serratec.projetoFinal.dto.FuncionarioDTO;
import org.serratec.projetoFinal.dto.FuncionarioInserirDTO;
import org.serratec.projetoFinal.exception.CpfException;
import org.serratec.projetoFinal.exception.EmailException;
import org.serratec.projetoFinal.exception.SenhaException;
import org.serratec.projetoFinal.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private MailConfig mailConfig;
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	BCryptPasswordEncoder encoder;

	public FuncionarioDTO inserir(FuncionarioInserirDTO funcionarioIns) 
			throws EmailException, SenhaException, CpfException {
		if (funcionarioRepository.findByEmail(funcionarioIns.getEmail()) != null) {
			throw new EmailException("Usuário já cadastrado.");
		}
		if (funcionarioRepository.findByCpf(funcionarioIns.getCpf()) != null) {
			throw new CpfException("Usuário já cadastrado.");
		}
		if (!funcionarioIns.getSenha().equals(funcionarioIns.getConfirmaSenha())) {
			throw new SenhaException("As senhas não coincidem");
		}

		Funcionario funcionario = new Funcionario(funcionarioIns);
		
		funcionario.setSenha(encoder.encode(funcionarioIns.getSenha())); //teste
		
		funcionario = funcionarioRepository.save(funcionario);
		
		//mailConfig.(funcionario.getEmail(), "Cadastro de Cliente", funcionario.toString());
		
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO(funcionario);
		

		return funcionarioDTO;
	}
	
	public List<FuncionarioDTO> listar() {
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		List<FuncionarioDTO> funcionarioDTO = new ArrayList<>();
		for (Funcionario funcionario : funcionarios) {
			funcionarioDTO.add(new FuncionarioDTO(funcionario));
		}
		return funcionarioDTO;
	}
	
	public void deletar(Long id) { 
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		if(funcionario.isPresent()) {
		funcionarioRepository.deleteById(id);
		}
	}
}
