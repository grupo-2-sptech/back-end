package collectiva.org.collecta.exception.global;

import collectiva.org.collecta.exception.utils.MessageErrorResponse;
import collectiva.org.collecta.exception.utils.MessageErrorResponseList;
import collectiva.org.collecta.exception.utils.RequestPath;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageErrorResponseList> erroValidacao(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errorMessages = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .toList();

        MessageErrorResponseList response = new MessageErrorResponseList(
                new Timestamp(System.currentTimeMillis()),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errorMessages,
                RequestPath.getRequestPath(request));


        return ResponseEntity.badRequest().body(response);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MismatchedInputException.class)
    public ResponseEntity<MessageErrorResponse> erroEnum(MismatchedInputException ex, HttpServletRequest request) {
        MessageErrorResponse response = new MessageErrorResponse(
                new Timestamp(System.currentTimeMillis()),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ("Valor inválido para o ENUM: '" + ex.getPath().get(0).getFieldName() + "'"),
                RequestPath.getRequestPath(request));

        return ResponseEntity.badRequest().body(response);
    }

    @ResponseBody
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<MessageErrorResponse> erroEnum(HttpClientErrorException ex, HttpServletRequest request) {
        MessageErrorResponse response = new MessageErrorResponse(
                new Timestamp(System.currentTimeMillis()),
                ex.getStatusCode().value(),
                ex.getStatusText(),
                "Ocorreu um erro durante a solicitação.",
                RequestPath.getRequestPath(request));

        return ResponseEntity.badRequest().body(response);
    }
}
