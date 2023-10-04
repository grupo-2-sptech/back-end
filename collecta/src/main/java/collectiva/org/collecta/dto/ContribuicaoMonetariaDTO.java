package collectiva.org.collecta.dto;

import collectiva.org.collecta.domain.enums.FormaPagamento;
import collectiva.org.collecta.domain.enums.StatusContribuicao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class ContribuicaoMonetariaDTO {
    private UUID id;

    @NotBlank(message = "O nome esta vazio")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
    private String nome;

    @NotBlank(message = "A descricao esta vazia")
    @Size(min = 3, message = "A descricao deve ter no mínimo 3 caracteres")
    private String descricao;

    private LocalDateTime dataHora;

    @DecimalMin(value = "0.01", message = "O valor deve ser maior ou igual a 0.01")
    private BigDecimal valor;

    @Positive(message = "As parcelas devem ser maior que zero")
    @Max(value =12, message = "As parcelas tem limite de 12")
    private Integer parcelas;

    @NotNull(message = "A forma de pagamento esta vazia")
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @Enumerated(EnumType.STRING)
    private StatusContribuicao statusContribuicao;

}
