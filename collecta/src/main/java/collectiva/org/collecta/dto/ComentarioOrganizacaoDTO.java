package collectiva.org.collecta.dto;

import collectiva.org.collecta.domain.enums.TipoConta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class ComentarioOrganizacaoDTO {
    private UUID id;
    @NotBlank(message = "O comentario esta vazio")
    @Size(min = 3, message = "O comentario deve ter no mínimo 3 caracteres")
    @Size(max = 255, message = "O comentario deve ter no máximo 255 caracteres")
    private String comentario;
    private LocalDateTime data;
    private TipoConta tipoConta;

}
