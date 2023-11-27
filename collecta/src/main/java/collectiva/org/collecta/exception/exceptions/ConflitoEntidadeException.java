package collectiva.org.collecta.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflitoEntidadeException extends RuntimeException{
    public ConflitoEntidadeException(Object entidade) {
        super(String.format("Conflito! %s já cadastrado no sistema", entidade.toString()));
    }
}