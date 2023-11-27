package collectiva.org.collecta.domain.plano.dto;

import collectiva.org.collecta.domain.conta.doador.dto.AssociationDoadorDTO;
import collectiva.org.collecta.enums.StatusPlano;
import collectiva.org.collecta.enums.TipoPlano;
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
public class ResponsePlanoDTO {
    private UUID id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private TipoPlano tipoPlano;
    private StatusPlano statusPlano;
    private AssociationDoadorDTO doador;
}
