package collectiva.org.collecta.domain.plano.dto;

import collectiva.org.collecta.enums.StatusPlano;
import collectiva.org.collecta.enums.TipoPlano;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CreatePlanoDTO {
    @NotNull(message = "A data do fim está vazia")
    @Future(message = "A data do fim deve estar no futuro")
    private LocalDateTime dataFim;

    @NotNull(message = "O tipo de plano está vazio")
    @Enumerated(EnumType.STRING)
    private TipoPlano tipoPlano;

    @NotNull(message = "O status do plano está vazio")
    @Enumerated(EnumType.STRING)
    private StatusPlano statusPlano;

    @NotNull(message = "O id do doador está vazio")
    private UUID idDoador;
}
