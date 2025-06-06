package org.serratec.projetoFinal.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<String> erros = new ArrayList<>();
		for (FieldError err: ex.getBindingResult().getFieldErrors()) {
			erros.add(err.getField() + ": " + err.getDefaultMessage());
		}
		ErroResposta erroResposta = new ErroResposta(status.value(), "Existem campos inválidos.", LocalDateTime.now(), erros);

		return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<String> erros = new ArrayList<>();
		erros.add(ex.getMessage() + ": " + ex.getMostSpecificCause());
		ErroResposta erroResposta = new ErroResposta(status.value(), "Confira a formatação do JSON", LocalDateTime.now(), erros);
return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
	}
	
	@ExceptionHandler({EmailException.class, SenhaException.class, CpfException.class, CategoriaException.class, UsuarioNaoPermitidoException.class
		,CodigoExpiradoException.class, PedidoNaoPodeSerAlteradoException.class})
	protected ResponseEntity<Object> handleException(RuntimeException ex){
		return ResponseEntity.unprocessableEntity().body(ex.getMessage());
	}
	
	@ExceptionHandler(NaoEncontradoException.class)
	public ResponseEntity<Map<String, String>> naoEncontrado(NaoEncontradoException ex){
		Map<String, String> erro = new HashMap<>();
		erro.put("erro", ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	}
	

