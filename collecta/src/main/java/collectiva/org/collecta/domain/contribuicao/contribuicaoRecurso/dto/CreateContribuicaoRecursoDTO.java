package collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.dto;

import collectiva.org.collecta.enums.TipoRecurso;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CreateContribuicaoRecursoDTO {
    private LocalDateTime dataHora;

    @NotNull(message = "A quantidade está vazia")
    @Positive(message = "A quantidade devem ser maior que zero")
    private Integer quantidade;

    @NotNull(message = "O Tipo de recurso está vazio")
    @Enumerated(EnumType.STRING)
    private TipoRecurso tipoRecurso;

    @NotNull(message = "O id do doador está vazio")
    private UUID idDoador;

    @NotNull(message = "O id do recurso está vazio")
    private UUID idRecurso;

}
