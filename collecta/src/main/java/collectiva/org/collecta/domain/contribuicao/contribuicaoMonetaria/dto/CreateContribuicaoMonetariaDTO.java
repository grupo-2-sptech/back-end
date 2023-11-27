package collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.dto;

import collectiva.org.collecta.enums.FormaPagamento;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class CreateContribuicaoMonetariaDTO {
    @DecimalMin(value = "0.01", message = "O valor deve ser maior ou igual a 0.01")
    private BigDecimal valor;

    @Positive(message = "As parcelas devem ser maior que zero")
    @Max(value = 12, message = "As parcelas tem limite de 12")
    private Integer parcelas;

    @NotNull(message = "A forma de pagamento está vazia")
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @NotNull(message = "O id do doador está vazio")
    private UUID idDoador;

    @NotNull(message = "O id do financeiro está vazio")
    private UUID idFinanceiro;

    private String txid;

    private String idCodigoPix;

}
