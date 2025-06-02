package org.serratec.projetoFinal.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.serratec.projetoFinal.config.MailConfig;
import org.serratec.projetoFinal.domain.CodigoRecuperacao;
import org.serratec.projetoFinal.domain.Usuario;
import org.serratec.projetoFinal.dto.RedefinirSenhaDTO;
import org.serratec.projetoFinal.dto.SolicitarRecuperacaoSenhaDTO;
import org.serratec.projetoFinal.exception.CodigoExpiradoException;
import org.serratec.projetoFinal.exception.NaoEncontradoException;
import org.serratec.projetoFinal.repository.CodigoRecuperacaoRepository;
import org.serratec.projetoFinal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	CodigoRecuperacaoRepository codigoRecuperacaoRepository;

	@Autowired
	MailConfig mailConfig;

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	BCryptPasswordEncoder encoder;

	public void solicitarRecuperacao(SolicitarRecuperacaoSenhaDTO recuperacaoEmail){
		Usuario usuario = usuarioRepository.findByEmail(recuperacaoEmail.getEmail());
		if (usuario == null) {
			throw new NaoEncontradoException("Nenhum usuário encontrado.");
		}
		CodigoRecuperacao codigoRecuperacao = new CodigoRecuperacao();
		
		String codigo = UUID.randomUUID().toString().substring(0, 8);

		LocalDateTime dataExpiracao = LocalDateTime.now().plusHours(1);


		codigoRecuperacao.setCodigo(codigo);
		codigoRecuperacao.setDataExpiracao(dataExpiracao);
		codigoRecuperacao.setUsuario(usuario);
		codigoRecuperacaoRepository.save(codigoRecuperacao);

		String email = usuario.getEmail();
		mailConfig.sendCodigoRecuperacao(email, codigo);
	}


	public void resetarSenha(RedefinirSenhaDTO redefinirSenha) {
		String codigoInformado = redefinirSenha.getCodigo();
		String novaSenha = redefinirSenha.getNovaSenha();
		
		CodigoRecuperacao codigo = codigoRecuperacaoRepository.findByCodigo(codigoInformado);
		if(codigo == null) {
			throw new NaoEncontradoException("Código informado inválido.");
		}
		if (codigo.getDataExpiracao().isBefore(LocalDateTime.now())){
			throw new CodigoExpiradoException("Código " + codigo.getCodigo() + " está expirado.");
		}
		Usuario usuario = codigo.getUsuario();
		usuario.setSenha(encoder.encode(novaSenha));
		
		usuarioRepository.save(usuario);
		codigoRecuperacaoRepository.delete(codigo);

	}
}
