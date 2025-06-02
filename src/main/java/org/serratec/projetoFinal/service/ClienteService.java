package org.serratec.projetoFinal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.projetoFinal.config.MailConfig;
import org.serratec.projetoFinal.domain.Cliente;
import org.serratec.projetoFinal.domain.ClienteEndereco;
import org.serratec.projetoFinal.domain.Endereco;
import org.serratec.projetoFinal.dto.ClienteAtualizarDTO;
import org.serratec.projetoFinal.dto.ClienteDTO;
import org.serratec.projetoFinal.dto.ClienteInserirDTO;
import org.serratec.projetoFinal.dto.EnderecoClienteDTO;
import org.serratec.projetoFinal.dto.EnderecoDTO;
import org.serratec.projetoFinal.dto.EnderecoInserirDTO;
import org.serratec.projetoFinal.exception.CpfException;
import org.serratec.projetoFinal.exception.EmailException;
import org.serratec.projetoFinal.exception.NaoEncontradoException;
import org.serratec.projetoFinal.exception.SenhaException;
import org.serratec.projetoFinal.exception.UsuarioNaoPermitidoException;
import org.serratec.projetoFinal.repository.ClienteEnderecoRepository;
import org.serratec.projetoFinal.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteService {


	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private ClienteEnderecoRepository clienteEnderecoRepository;

	@Autowired
	private MailConfig mailConfig;

	@Autowired
	private AutenticacaoService autenticacaoService;

	@Autowired
	BCryptPasswordEncoder encoder;



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

		cliente.setSenha(encoder.encode(clienteIns.getSenha()));

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

	//teste, favor corrigir depois
	/*public ClienteDTO listarId(Long id) {
		 Optional<Cliente> nome = clienteRepository.findById(id);
		 if(nome.isPresent()) {
			 Cliente cliente = nome.get();
			 ClienteDTO clienteDTO = new ClienteDTO(cliente);
			 return clienteDTO;
		 }
		 return null;
	}*/

	public ClienteDTO buscarDados() { // por autenticação
		Cliente cliente= autenticacaoService.clienteAutenticacao();
		ClienteDTO clienteDTO = new ClienteDTO(cliente);
		return clienteDTO;
	}

	public void deletar() { // por autenticação
		Cliente cliente = autenticacaoService.clienteAutenticacao();
		clienteRepository.delete(cliente);
	}

	public EnderecoClienteDTO inserirEndereco (EnderecoInserirDTO enderecoIns) { // por autenticação
		Cliente cliente = autenticacaoService.clienteAutenticacao();
		Endereco endereco = enderecoService.buscarInserir(enderecoIns.getCep());

		ClienteEndereco clienteE = new ClienteEndereco(cliente, endereco);

		clienteEnderecoRepository.save(clienteE);

		cliente.getEnderecos().add(clienteE);
		endereco.getClientes().add(clienteE); // trocar depois talvez

		EnderecoClienteDTO enderecoC = new EnderecoClienteDTO (clienteE);
		return enderecoC;

	}

	/*	public ClienteDTO atualizarPorId(Long id, ClienteInserirDTO clienteIns) {
		Optional<Cliente> clienteOpt = clienteRepository.findById(id);
		if(clienteOpt.isPresent()) {
			Cliente cliente = clienteOpt.get();
			cliente.setNome(clienteIns.getNome());
			cliente.setTelefone(clienteIns.getTelefone());
			cliente.setSenha(clienteIns.getSenha());
			cliente = clienteRepository.save(cliente);
			ClienteDTO clienteDTO = new ClienteDTO(cliente);
			mailConfig.sendEmailAtt(cliente.getEmail(), "Atualização de cadastro do cliente", cliente.toString());
			return clienteDTO;
		}
		return null;
	}*/

	public ClienteDTO atualizar(ClienteAtualizarDTO clienteAt) { // personalizar mais as mensagens de erro depois, se quiser
		Cliente cliente = autenticacaoService.clienteAutenticacao();

		if (clienteAt.getNome() != null) {
			if (clienteAt.getNome().length() < 3 || clienteAt.getNome().length() > 150) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome deve ter entre 3 e 150 caracteres.");
			}
			cliente.setNome(clienteAt.getNome());
		}
		if (clienteAt.getSenha() != null) {
			if (clienteAt.getSenha().length() < 8 ) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Senha deve ter no mínimo 8 caracteres.");
			}
			String senhaCriptografada = encoder.encode(clienteAt.getSenha());
			cliente.setSenha(senhaCriptografada);
		}
		if (clienteAt.getTelefone() != null) {
			if (!clienteAt.getTelefone().matches("\\d{11}")) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Telefone deve ter 11 dígitos.");
			}
			cliente.setTelefone(clienteAt.getTelefone());
		}

		cliente = clienteRepository.save(cliente);

		ClienteDTO clienteDTO = new ClienteDTO(cliente);
		mailConfig.sendEmailAtt(cliente.getEmail(), "Atualização de cadastro do cliente", cliente.toString());
		return clienteDTO;
	}
	
	public void deletarEndereço(Long Id) {
		Cliente cliente = autenticacaoService.clienteAutenticacao();
		List<ClienteEndereco> enderecos = cliente.getEnderecos();
		Optional<ClienteEndereco> enderecoOpt = clienteEnderecoRepository.findById(Id);
		if(enderecoOpt.isPresent()){
			ClienteEndereco clienteEndereco = enderecoOpt.get();
			 if (!cliente.getEnderecos().contains(clienteEndereco)) {
			        throw new UsuarioNaoPermitidoException("Endereço não pertence ao cliente autenticado");
			    }
			enderecos.remove(clienteEndereco);
			clienteRepository.save(cliente);
			clienteEnderecoRepository.delete(clienteEndereco);
		} else {
		throw new NaoEncontradoException("Endereço não encontrado");
		}
		
	}
}
