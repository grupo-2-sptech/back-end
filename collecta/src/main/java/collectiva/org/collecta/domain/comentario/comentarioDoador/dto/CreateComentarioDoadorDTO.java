package collectiva.org.collecta.domain.comentario.comentarioDoador.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreateComentarioDoadorDTO {
    @NotBlank(message = "O comentário está vazio")
    @Size(min = 3, message = "O comentário deve ter no mínimo 3 caracteres")
    @Size(max = 255, message = "O comentário deve ter no máximo 255 caracteres")
    private String comentario;

    @NotNull(message = "O id do doador está vazio")
    private UUID idDoador;
}
