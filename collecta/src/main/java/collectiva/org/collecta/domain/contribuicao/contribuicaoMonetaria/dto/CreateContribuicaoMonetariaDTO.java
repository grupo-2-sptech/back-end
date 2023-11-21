package collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.dto;

import collectiva.org.collecta.enums.FormaPagamento;
import collectiva.org.collecta.enums.StatusContribuicao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class CreateContribuicaoMonetariaDTO {
    @NotBlank(message = "O nome está vazio")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
    private String nome;

    @NotBlank(message = "A descrição esta vazia")
    @Size(min = 3, message = "A descrição deve ter no mínimo 3 caracteres")
    private String descricao;

    private LocalDateTime dataHora;

    @DecimalMin(value = "0.01", message = "O valor deve ser maior ou igual a 0.01")
    private BigDecimal valor;

    @Positive(message = "As parcelas devem ser maior que zero")
    @Max(value = 12, message = "As parcelas tem limite de 12")
    private Integer parcelas;

    @NotNull(message = "A forma de pagamento está vazia")
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @NotNull(message = "O status da contribuição está vazio")
    @Enumerated(EnumType.STRING)
    private StatusContribuicao statusContribuicao;

}
