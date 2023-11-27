package collectiva.org.collecta.domain.financeiroCampanha.dto;

import collectiva.org.collecta.enums.MetaStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class UpdateFinanceiroCampanhaDTO {
    @NotNull(message = "O valor meta está vazio")
    @DecimalMin(value = "0.1", message = "O valor meta deve ser maior que zero")
    private BigDecimal valorMeta;

    @NotNull(message = "O modo de contribuição não pode ser nulo")
    @Enumerated(EnumType.STRING)
    private MetaStatus metaStatus;
}
