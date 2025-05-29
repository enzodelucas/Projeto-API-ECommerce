package org.serratec.projetoFinal.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.projetoFinal.config.MailConfig;
import org.serratec.projetoFinal.config.MailConfig;
import org.serratec.projetoFinal.domain.Cliente;
import org.serratec.projetoFinal.dto.ClienteDTO;
import org.serratec.projetoFinal.dto.ClienteInserirDTO;
import org.serratec.projetoFinal.exception.CpfException;
import org.serratec.projetoFinal.exception.EmailException;
import org.serratec.projetoFinal.exception.SenhaException;
import org.serratec.projetoFinal.repository.ClienteRepository;
import org.serratec.projetoFinal.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {


	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
   
	@Autowired
	private MailConfig mailConfig;
	
    
	public ClienteDTO inserir(ClienteInserirDTO clienteIns) 
			throws EmailException, SenhaException, CpfException {
		if (clienteRepository.findByEmail(clienteIns.getEmail()) != null) {
			throw new EmailException("Usuário já cadastrado.");
		}
		if (clienteRepository.findByCpf(clienteIns.getCpf()) != null) {
			throw new CpfException("Usuário já cadastrado.");
		}
		if (!clienteIns.getSenha().equals(clienteIns.getConfirmaSenha())) {
			throw new SenhaException("As senhas não coincidem");
		}

		Cliente cliente = new Cliente(clienteIns);
		
		cliente = clienteRepository.save(cliente);
		
		mailConfig.sendEmail(cliente.getEmail(), "Cadastro de Cliente", cliente.toString());
		
		ClienteDTO clienteDTO = new ClienteDTO(cliente);
		

		return clienteDTO;

	}
	
	public List<ClienteDTO> listar() {
		List<Cliente> clientes = clienteRepository.findAll();
		List<ClienteDTO> clienteDTO = new ArrayList<>();
		for (Cliente cliente : clientes) {
			clienteDTO.add(new ClienteDTO(cliente));
		}
		return clienteDTO;
	}

}
