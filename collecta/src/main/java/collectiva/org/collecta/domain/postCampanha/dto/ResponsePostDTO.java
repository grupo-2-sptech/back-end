package collectiva.org.collecta.domain.postCampanha.dto;

import collectiva.org.collecta.domain.comentario.comentario.AssociationComentarioDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ResponsePostDTO {
    private UUID id;
    private String titulo;
    private String conteudo;
    private LocalDateTime data;
    private int likes;
    private List<AssociationComentarioDTO> comentarios;
}
