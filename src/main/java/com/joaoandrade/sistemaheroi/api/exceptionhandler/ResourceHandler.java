package com.joaoandrade.sistemaheroi.api.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.joaoandrade.sistemaheroi.domain.exception.EntidadeNaoEncontradaException;
import com.joaoandrade.sistemaheroi.domain.exception.SistemaException;

@ControllerAdvice
public class ResourceHandler extends ResponseEntityExceptionHandler {

	private static final String DEFAULT_MESSAGE = "Ocorreu um erro inesperado, se o problem persistir recomendo que entre em contato com a nossa equipe de suporte.";

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleErroGenerico(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Error error = Error.ERRO_INTERNO_NO_SERVIDOR;
		String message = "Ocorreu um erro interno no servidor.";
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(), message,
				DEFAULT_MESSAGE);

		return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(SistemaException.class)
	public ResponseEntity<Object> handleSistema(SistemaException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Error error = Error.SISTEMA_EXCEPTION;
		String message = ex.getMessage();
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(), message,
				message);

		return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		Error error = Error.ENTIDADE_NAO_ENCONTRADA_EXCEPTION;
		String message = ex.getMessage();
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(), message,
				message);

		return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Error error = Error.ERRO_VALIDACAO_DOS_DADOS;
		String message = "Ocorreu um erro de validação dos dados.";
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(), message,
				message);

		for (ObjectError objectError : ex.getAllErrors()) {
			String field = objectError.getObjectName();

			if (objectError instanceof FieldError) {
				field = ((FieldError) objectError).getField();
			}

			String userMesage = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
			problemDetail.adicionarError(field, userMesage);
		}

		return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable cause = ex.getCause();

		if (cause instanceof UnrecognizedPropertyException) {
			return handleUnrecognizedProperty((UnrecognizedPropertyException) cause, headers, status, request);
		}

		Error error = Error.ERRO_DESSERIALIZACAO_JSON;
		String message = "Ocorreu um erro na desserialização do JSON. Verifique se os dados foram inseridos corretamente no corpo da requisição.";
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(), message,
				DEFAULT_MESSAGE);

		return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	private ResponseEntity<Object> handleUnrecognizedProperty(UnrecognizedPropertyException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Error error = Error.PROPRIEDADE_INEXISTENTE;
		String message = String.format("A Propriedade '%s' é inexistente!", ex.getPropertyName());
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(), message,
				DEFAULT_MESSAGE);

		return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (body == null) {
			body = new ProblemDetail(null, null, status.value(), status.getReasonPhrase(), DEFAULT_MESSAGE);
		}

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

}
