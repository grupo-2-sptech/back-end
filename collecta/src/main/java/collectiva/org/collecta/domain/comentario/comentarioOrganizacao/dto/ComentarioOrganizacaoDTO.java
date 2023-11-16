package collectiva.org.collecta.domain.comentario.comentarioOrganizacao.dto;

import collectiva.org.collecta.enums.TipoConta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ComentarioOrganizacaoDTO {
    private UUID id;
    @NotBlank(message = "O comentário está vazio")
    @Size(min = 3, message = "O comentário deve ter no mínimo 3 caracteres")
    @Size(max = 255, message = "O comentário deve ter no máximo 255 caracteres")
    private String comentario;
    private LocalDateTime data;
    private TipoConta tipoConta;

}
