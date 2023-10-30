package collectiva.org.collecta.dto;

import collectiva.org.collecta.domain.enums.StatusPlano;
import collectiva.org.collecta.domain.enums.TipoPlano;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class PlanoDTO {
    private UUID id;

    @NotNull(message = "A data do inicio está vazia")
    @PastOrPresent(message = "A data do inicio deve estar no passado ou presente")
    private LocalDateTime dataInicio;

    @NotNull(message = "A data do fim está vazia")
    @Future(message = "A data do fim deve estar no futuro")
    private LocalDateTime dataFim;

    @NotNull(message = "O tipo de plano está vazio")
    @Enumerated(EnumType.STRING)
    private TipoPlano tipoPlano;

    @NotNull(message = "O status do plano está vazio")
    @Enumerated(EnumType.STRING)
    private StatusPlano statusPlano;
}
