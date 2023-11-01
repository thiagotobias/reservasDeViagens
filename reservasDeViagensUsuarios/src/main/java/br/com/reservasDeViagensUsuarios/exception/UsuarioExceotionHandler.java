package br.com.reservasDeViagensUsuarios.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UsuarioExceotionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsuarioException.class)
    public ResponseEntity<Object> handleEventNotFound(UsuarioException exception, WebRequest request) {

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.PRECONDITION_FAILED, exception.getMessage());
        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }

}
