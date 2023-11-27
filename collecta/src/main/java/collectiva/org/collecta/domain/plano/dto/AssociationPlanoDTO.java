package collectiva.org.collecta.domain.plano.dto;

import collectiva.org.collecta.enums.StatusPlano;
import collectiva.org.collecta.enums.TipoPlano;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class AssociationPlanoDTO {
    private UUID id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private TipoPlano tipoPlano;
    private StatusPlano statusPlano;
}
