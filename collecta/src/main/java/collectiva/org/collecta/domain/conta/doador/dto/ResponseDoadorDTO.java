package collectiva.org.collecta.domain.conta.doador.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ResponseDoadorDTO {
    private UUID id;
    private String email;
    private String telefone;
    private String nome;
    private String sobrenome;
}
