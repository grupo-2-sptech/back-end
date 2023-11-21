package collectiva.org.collecta.domain.eventoCampanha.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ResponseEventoCampanhaDTO {
    private UUID id;
    private String nome;
    private String descricao;
    private LocalDateTime dataHora;

}
