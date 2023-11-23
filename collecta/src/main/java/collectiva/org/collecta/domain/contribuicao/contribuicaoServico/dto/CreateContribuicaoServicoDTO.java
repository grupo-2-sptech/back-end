package collectiva.org.collecta.domain.contribuicao.contribuicaoServico.dto;

import collectiva.org.collecta.enums.StatusContribuicao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CreateContribuicaoServicoDTO {
    @NotBlank(message = "O nome está vazio")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
    private String nome;

    @NotBlank(message = "A descrição está vazia")
    @Size(min = 3, message = "A descrição deve ter no mínimo 3 caracteres")
    private String descricao;

    private LocalDateTime dataHora;

    @NotNull(message = "A data de inicio está vazia")
    @Past(message = "A data de inicio deve estar no passado ")
    private LocalDateTime horaInicio;

    @NotNull(message = "A hora do fim está vazia")
    @Past(message = "A hora do fim deve estar no passado")
    private LocalDateTime horaFim;

    @NotNull(message = "A avaliação está vazia")
    @Positive(message = "A avaliação devem ser entre 0 a 10")
    @Max(value = 10, message = "A avaliação devem ser entre 0 a 10")
    private Integer avaliacao;

    @NotNull(message = "O status da contribuição está vazio")
    @Enumerated(EnumType.STRING)
    private StatusContribuicao statusContribuicao;

    @NotNull(message = "O id do doador está vazio")
    private UUID idDoador;

}
