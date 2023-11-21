package collectiva.org.collecta.domain.relatorio.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CreateRelatorioDTO {
    @NotNull(message = "O valor arrecadado está vazio")
    @DecimalMin(value = "0.1", message = "O valor arrecadado deve ser maior que zero")
    private BigDecimal valorArrecadado;

    @NotNull
    @Min(value = 0, message = "O número de quantidade de doações deve ser pelo menos 0")
    private int quantidadeFinanceirosCampanha;
}
