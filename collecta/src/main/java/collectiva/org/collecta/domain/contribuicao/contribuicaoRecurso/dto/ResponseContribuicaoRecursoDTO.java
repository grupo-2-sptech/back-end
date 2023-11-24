package collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.dto;

import collectiva.org.collecta.enums.StatusContribuicao;
import collectiva.org.collecta.enums.TipoRecurso;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ResponseContribuicaoRecursoDTO {
    private UUID id;
    private LocalDateTime dataHora;
    private Integer quantidade;
    private TipoRecurso tipoRecurso;
    private StatusContribuicao statusContribuicao;

}
