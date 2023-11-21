package collectiva.org.collecta.domain.comentario.comentarioOrganizacao.dto;

import collectiva.org.collecta.enums.TipoConta;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ResponseComentarioOrganizacaoDTO {
    private UUID id;
    private String comentario;
    private LocalDateTime data;
    private TipoConta tipoConta;

}
