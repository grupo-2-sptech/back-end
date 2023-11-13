package collectiva.org.collecta.domain.postCampanha.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class PostDTO {
    private UUID id;

    @NotBlank(message = "O titulo está vazio")
    @Size(min = 3, message = "O titulo deve ter no mínimo 3 caracteres")
    private String titulo;

    @NotBlank(message = "O conteúdo está vazio")
    @Size(min = 3, message = "O conteúdo deve ter no mínimo 3 caracteres")
    private String conteudo;
    private LocalDateTime data;
}