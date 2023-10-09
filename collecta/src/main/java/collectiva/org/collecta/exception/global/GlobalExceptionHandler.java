package collectiva.org.collecta.exception.global;

import collectiva.org.collecta.exception.ErrorResponse;
import collectiva.org.collecta.exception.ErrorResponseList;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoContemElementosException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.ObjectInputFilter;
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

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseList> erroValidacao(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errorMessages = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponseList response = new ErrorResponseList(
                new Timestamp(System.currentTimeMillis()),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errorMessages,
                getRequestPath(request));


        return ResponseEntity.badRequest().body(response);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MismatchedInputException.class)
    public ResponseEntity<ErrorResponse> erroEnum(MismatchedInputException ex, HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse(
                new Timestamp(System.currentTimeMillis()),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ("Valor inválido para o ENUM: '" + ex.getPath().get(0).getFieldName() + "'"),
                getRequestPath(request));

        return ResponseEntity.badRequest().body(response);
    }


    private String getRequestPath(HttpServletRequest request) {
        if (request != null) {
            return request.getRequestURI();
        }
        return "/";
    }
}
