package collectiva.org.collecta.domain.financeiroCampanha.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class CreateFinanceiroCampanhaDTO {
    @NotNull(message = "O valor meta está vazio")
    @DecimalMin(value = "0.1", message = "O valor meta deve ser maior que zero")
    private BigDecimal valorMeta;

    @NotNull(message = "O id da campanha está vazio")
    private UUID idCampanha;
}
