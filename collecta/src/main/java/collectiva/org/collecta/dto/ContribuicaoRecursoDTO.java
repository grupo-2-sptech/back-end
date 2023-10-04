package collectiva.org.collecta.dto;

import collectiva.org.collecta.domain.enums.StatusContribuicao;
import collectiva.org.collecta.domain.enums.TipoRecurso;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class ContribuicaoRecursoDTO {
    private UUID id;

    @NotBlank(message = "O nome esta vazio")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
    private String nome;

    @NotBlank(message = "A descricao esta vazia")
    @Size(min = 3, message = "A descricao deve ter no mínimo 3 caracteres")
    private String descricao;

    private LocalDateTime dataHora;

    @NotNull(message = "A quantidade esta vazia")
    @Positive(message = "A quantidade devem ser maior que zero")
    private Integer quantidade;

    @NotNull(message = "O Tipo de recurso esta vazio")
    @Enumerated(EnumType.STRING)
    private TipoRecurso tipoRecurso;

    @Enumerated(EnumType.STRING)
    private StatusContribuicao statusContribuicao;

}
