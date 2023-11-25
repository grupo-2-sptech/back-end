package collectiva.org.collecta.domain.contribuicao.contribuicaoServico.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CreateContribuicaoServicoDTO {
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

    @NotNull(message = "O id do doador está vazio")
    private UUID idDoador;

    @NotNull(message = "O id do evento está vazio")
    private UUID idEvento;

}
