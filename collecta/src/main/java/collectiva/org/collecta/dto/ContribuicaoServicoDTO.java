package collectiva.org.collecta.dto;

import collectiva.org.collecta.domain.enums.StatusContribuicao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class ContribuicaoServicoDTO {
    private UUID id;

    @NotBlank(message = "O nome esta vazio")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
    private String nome;

    @NotBlank(message = "A descricao esta vazia")
    @Size(min = 3, message = "A descricao deve ter no mínimo 3 caracteres")
    private String descricao;

    private LocalDateTime dataHora;

    @NotNull(message = "A data de inicio esta vazia")
    @Past(message = "A data de inicio deve estar no passado ")
    private LocalDateTime horaInicio;

    @NotNull(message = "A hora do fim esta vazia")
    @Past(message = "A hora do fim deve estar no passado")
    private LocalDateTime horaFim;

    @NotNull(message = "A avaliacao esta vazia")
    @Positive(message = "A avaliacao devem ser entre 0 a 10")
    @Max(value = 10, message = "A avaliacao devem ser entre 0 a 10")
    private Integer avaliacao;


    @Enumerated(EnumType.STRING)
    private StatusContribuicao statusContribuicao;

}
