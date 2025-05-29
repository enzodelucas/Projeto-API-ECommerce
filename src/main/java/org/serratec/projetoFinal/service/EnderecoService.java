package org.serratec.projetoFinal.service;

import java.util.Optional;

import org.serratec.projetoFinal.domain.Endereco;
import org.serratec.projetoFinal.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;

	public Endereco buscar(String cep) {
		Optional<Endereco> enderecoOpt = enderecoRepository.findByCep(cep);
		if (enderecoOpt.isPresent()) {
			return enderecoOpt.get();
		} else {
			// buscando na API Externa (viacep)
			RestTemplate restTemplate = new RestTemplate();
			String url = "http://viacep.com.br/ws/" + cep + "/json/";
			Optional<Endereco> enderecoViaCepOpt = Optional.ofNullable(restTemplate.getForObject(url, Endereco.class));
			if (enderecoViaCepOpt.isPresent() && enderecoViaCepOpt.get().getCep() != null) {
				Endereco enderecoViaCep = enderecoViaCepOpt.get();
				String cepSemTraco = enderecoViaCep.getCep().replaceAll("-", "");
				enderecoViaCep.setCep(cepSemTraco);
				//return inserir(enderecoViaCep);
			} else {
				return null;
			}
		}
		return null; //para nao dar erros;

	}
}
