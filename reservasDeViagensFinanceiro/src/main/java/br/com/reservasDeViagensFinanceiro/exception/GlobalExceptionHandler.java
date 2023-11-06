package br.com.reservasDeViagensFinanceiro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.reservasDeViagensFinanceiro.model.dto.ApiErrorMessage;
import jakarta.validation.ConstraintDefinitionException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({TransacaoFinanceiraException.class, ReservaViagemException.class})
    public ApiErrorMessage handleNotFoundException(RuntimeException e) {
        ApiErrorMessage error = new ApiErrorMessage();
        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(e.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        return error;
    }
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiErrorMessage> validationHandler(MethodArgumentNotValidException e) {
        ApiErrorMessage error = new ApiErrorMessage();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(e.getFieldError().getDefaultMessage());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler({ConstraintViolationException.class, ConstraintDefinitionException.class})
    public ResponseEntity<ApiErrorMessage> validationHandler(ValidationException e) {
        ApiErrorMessage error = new ApiErrorMessage();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(e.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiErrorMessage> genericHandler(Exception e) {
        ApiErrorMessage error = new ApiErrorMessage();
        error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(e.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

