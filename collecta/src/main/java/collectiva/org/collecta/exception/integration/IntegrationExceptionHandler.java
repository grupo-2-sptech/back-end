package collectiva.org.collecta.exception.integration;

import collectiva.org.collecta.exception.utils.MessageErrorResponse;
import collectiva.org.collecta.exception.utils.RequestPath;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;

@ControllerAdvice
public class IntegrationExceptionHandler {
    @ExceptionHandler(IntegrationException.class)
    public ResponseEntity<MessageErrorResponse> integracaoException(IntegrationException ex, HttpServletRequest request) {
        MessageErrorResponse response = new MessageErrorResponse(
                new Timestamp(System.currentTimeMillis()),
                HttpStatus.valueOf(ex.getStatus()).value(),
                HttpStatus.valueOf(ex.getStatus()).getReasonPhrase(),
                ex.getMessage(),
                RequestPath.getRequestPath(request));

        return ResponseEntity.status(HttpStatus.valueOf(ex.getStatus())).body(response);
    }

}