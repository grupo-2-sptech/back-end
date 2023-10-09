package collectiva.org.collecta.exception.integration;

import lombok.Data;

@Data
public class IntegrationException extends RuntimeException {
    private Integer status;
    private String caminho;

    public IntegrationException(String message, Integer status, String caminho) {
        super(message);
        this.status = status;
        this.caminho = caminho;
    }
}
