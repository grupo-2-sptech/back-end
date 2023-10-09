package collectiva.org.collecta.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class EntidadeNaoContemElementosException extends RuntimeException {
    public EntidadeNaoContemElementosException(Object entidade) {
        super(String.format("%s está vazia", entidade.toString()));
    }

}
