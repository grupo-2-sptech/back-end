package collectiva.org.collecta.exception.error;

import collectiva.org.collecta.exception.exceptions.ConflitoEntidadeException;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import collectiva.org.collecta.exception.utils.MessageErrorResponse;
import collectiva.org.collecta.exception.utils.RequestPath;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.Timestamp;

@ControllerAdvice
public class ErrorExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<MessageErrorResponse> entidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, HttpServletRequest request) {
        MessageErrorResponse response = new MessageErrorResponse(
                new Timestamp(System.currentTimeMillis()),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                RequestPath.getRequestPath(request));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ConflitoEntidadeException.class)
    public ResponseEntity<MessageErrorResponse> conflitoEntidadeException(ConflitoEntidadeException ex, HttpServletRequest request) {
        MessageErrorResponse response = new MessageErrorResponse(
                new Timestamp(System.currentTimeMillis()),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                RequestPath.getRequestPath(request));

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

}