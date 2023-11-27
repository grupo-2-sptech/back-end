package collectiva.org.collecta.domain.comentario.comentarioDoador.dto;

import collectiva.org.collecta.domain.conta.doador.dto.AssociationDoadorDTO;
import collectiva.org.collecta.domain.postCampanha.dto.AssociationPostDTO;
import collectiva.org.collecta.enums.TipoConta;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ResponseComentarioDoadorDTO {
    private UUID id;
    private String comentario;
    private LocalDateTime data;
    private TipoConta tipoConta;
    private AssociationDoadorDTO doador;
    private AssociationPostDTO post;

}
