package org.serratec.projetoFinal.service;

import java.util.List;
import java.util.Optional;

import org.serratec.projetoFinal.domain.Categoria;
import org.serratec.projetoFinal.domain.Cliente;
import org.serratec.projetoFinal.domain.ClienteEndereco;
import org.serratec.projetoFinal.domain.Endereco;
import org.serratec.projetoFinal.dto.EnderecoAtualizarDTO;
import org.serratec.projetoFinal.dto.EnderecoDTO;
import org.serratec.projetoFinal.exception.CategoriaException;
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
	
	

	public EnderecoDTO buscarInserirDTO(String cep) {
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
	}
	
	
	
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
	public ClienteEndereco atualizarEndereco(EnderecoAtualizarDTO endAtuDTO, Cliente cliente) {
		Long idCliente = cliente.getId();
		Optional<ClienteEndereco> endeClienteOpt = clienteEnderecoRepository.findFirstByClienteId(idCliente);
		if(endeClienteOpt.isPresent()) {
			ClienteEndereco ce = endeClienteOpt.get();
			ce.setComplemento(endAtuDTO.getComplemento());
			ce.setNumero(endAtuDTO.getNumero());
			clienteEnderecoRepository.save(ce);		
			return ce;
		}
		throw new NaoEncontradoException("Cliente não foi encontrado");
	}
	
}

