package collectiva.org.collecta.dto;

import collectiva.org.collecta.domain.enums.FormaPagamento;
import collectiva.org.collecta.domain.enums.Plano;
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
public class PagamentoDTO {
    private UUID id;

    @NotNull(message = "O plano não pode ser nulo")
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @NotNull(message = "O valor não pode ser nulo")
    @DecimalMin(value = "0.1", message = "O valor deve ser maior que zero")
    private BigDecimal valor;

    @NotNull(message = "O plano não pode ser nulo")
    @Enumerated(EnumType.STRING)
    private Plano plano;

    private LocalDateTime dataHora;

    @Min(value = 1, message = "O número de parcelas deve ser pelo menos 1")
    @Max(value = 15, message = "O número de parcelas não pode exceder 15")
    private int parcelas;
}
