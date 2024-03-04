package collectiva.org.collecta.domain.comentario.comentarioOrganizacao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreateComentarioOrganizacaoDTO {
    @NotBlank(message = "O comentário está vazio")
    @Size(min = 3, max = 255, message = "O comentário deve ter no mínimo 3 e no máximo 255 caracteres")
    private String comentario;
}
