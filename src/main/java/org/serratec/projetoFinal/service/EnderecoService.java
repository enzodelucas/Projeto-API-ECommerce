package org.serratec.projetoFinal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.projetoFinal.domain.Cliente;
import org.serratec.projetoFinal.domain.ClienteEndereco;
import org.serratec.projetoFinal.domain.Endereco;
import org.serratec.projetoFinal.dto.EnderecoAtualizarDTO;
import org.serratec.projetoFinal.dto.EnderecoClienteDTO;
import org.serratec.projetoFinal.exception.NaoEncontradoException;
import org.serratec.projetoFinal.repository.ClienteEnderecoRepository;
import org.serratec.projetoFinal.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	ClienteEnderecoRepository clienteEnderecoRepository;
	
	@Autowired
	AutenticacaoService autenticacaoService;

	/*public EnderecoDTO buscarInserirDTO(String cep) {
		Optional<Endereco> enderecoOpt = enderecoRepository.findByCep(cep);
		if (enderecoOpt.isPresent()) {
			EnderecoDTO enderecoDTO = new EnderecoDTO(enderecoOpt.get());
			return enderecoDTO;
		} else {
			RestTemplate restTemplate = new RestTemplate();
			String url = "http://viacep.com.br/ws/" + cep + "/json/";
			Optional<Endereco> enderecoViaCepOpt = Optional.ofNullable(restTemplate.getForObject(url, Endereco.class));
			if (enderecoViaCepOpt.isPresent() && enderecoViaCepOpt.get().getCep() != null) {
				Endereco enderecoViaCep = enderecoViaCepOpt.get();
				String cepSemTraco = enderecoViaCep.getCep().replaceAll("-", "");
				enderecoViaCep.setCep(cepSemTraco);
				return inserirDTO(enderecoViaCep);
			} else {
				return null;
			}
		}
		
	}
	
	public EnderecoDTO inserirDTO(Endereco endereco) {
		endereco = enderecoRepository.save(endereco);
		EnderecoDTO enderecoDto = new EnderecoDTO(endereco);
		return enderecoDto;
	}*/
	//se nao tiver sendo usado, apagar depois
	
	
	
	public Endereco buscarInserir(String cep) {
		Optional<Endereco> enderecoOpt = enderecoRepository.findByCep(cep);
		if (enderecoOpt.isPresent()) {
			return enderecoOpt.get();
		} else {
			RestTemplate restTemplate = new RestTemplate();
			String url = "http://viacep.com.br/ws/" + cep + "/json/";
			Optional<Endereco> enderecoViaCepOpt = Optional.ofNullable(restTemplate.getForObject(url, Endereco.class));
			if (enderecoViaCepOpt.isPresent() && enderecoViaCepOpt.get().getCep() != null) {
				Endereco enderecoViaCep = enderecoViaCepOpt.get();
				String cepSemTraco = enderecoViaCep.getCep().replaceAll("-", "");
				enderecoViaCep.setCep(cepSemTraco);
				return inserirTeste(enderecoViaCep);
			} else {
				return null;
			}
		}
		
	}
	
	
	public Endereco inserirTeste(Endereco endereco) {
		endereco = enderecoRepository.save(endereco);
		return endereco;
	}
	
	//funcionou porem não ta editando o existente, ta criando outro id a partir desse 
	//Acho que corrigir, confirmar depois
	public ClienteEndereco atualizarEndereco(EnderecoAtualizarDTO endAtuDTO, Cliente cliente) {
		Long idCliente = cliente.getId();
		Optional<ClienteEndereco> endeClienteOpt = clienteEnderecoRepository.findFirstByClienteId(idCliente);
		if(endeClienteOpt.isPresent()) {
			ClienteEndereco ce = endeClienteOpt.get();
			ce.setId(ce.getId()); // tentando corrigir o erro do id
			ce.setComplemento(endAtuDTO.getComplemento());
			ce.setNumero(endAtuDTO.getNumero());
			clienteEnderecoRepository.save(ce);		
			return ce;
		}
		throw new NaoEncontradoException("Cliente não foi encontrado");
	}
	
	public List<EnderecoClienteDTO> listarEnderecoCliente() {
        Cliente cliente = autenticacaoService.clienteAutenticacao();
        List<ClienteEndereco> clienteEndereco = cliente.getEnderecos();
        List<EnderecoClienteDTO> enderecoClienteDTO = new ArrayList();
        for (ClienteEndereco enderecoCliente : clienteEndereco) {
            EnderecoClienteDTO enderecoCli = new EnderecoClienteDTO(enderecoCliente);
            enderecoClienteDTO.add(enderecoCli);
        } 
        return enderecoClienteDTO;
    }
}

