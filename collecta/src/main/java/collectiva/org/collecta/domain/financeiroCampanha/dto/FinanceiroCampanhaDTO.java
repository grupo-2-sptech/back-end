package collectiva.org.collecta.domain.financeiroCampanha.dto;

import collectiva.org.collecta.enums.MetaStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class FinanceiroCampanhaDTO {
    private UUID id;

    @NotNull(message = "O valor atingido está vazio")
    @DecimalMin(value = "0.1", message = "O valor atingido deve ser maior que zero")
    private BigDecimal valorAtingido;

    @NotNull(message = "O valor meta está vazio")
    @DecimalMin(value = "0.1", message = "O valor meta deve ser maior que zero")
    private BigDecimal valorMeta;


    @NotNull(message = "O modo de contribuição não pode ser nulo")
    @Enumerated(EnumType.STRING)
    private MetaStatus metaStatus;
}
