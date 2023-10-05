package collectiva.org.collecta.exception;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> erroValidacao(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errorMessages = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse response = ErrorResponse.builder()
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .status(HttpStatus.BAD_REQUEST.value())
                .error("BAD REQUEST")
                .message(errorMessages)
                .path(getRequestPath(request))
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MismatchedInputException.class)
    public ResponseEntity<ErrorResponse> erroEnum(MismatchedInputException ex, HttpServletRequest request) {
        List<String> errorMessages = ex.getPath()
                .stream()
                .map(reference -> "Valor inválido para o ENUM: '" + reference.getFieldName() + "'")
                .collect(Collectors.toList());

        ErrorResponse response = ErrorResponse.builder()
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .status(HttpStatus.BAD_REQUEST.value())
                .error("BAD REQUEST")
                .message(errorMessages)
                .path(getRequestPath(request))
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    private String getRequestPath(HttpServletRequest request) {
        if (request != null) {
            return request.getRequestURI();
        }
        return "/";
    }
}
