package collectiva.org.collecta.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
public class PagamentoDTO {
    private UUID id;
    @NotBlank(message = "A forma de pagamento está vazia")
    @Size(min = 3, message = "A forma de pagamento deve ter no mínimo 3 caracteres")
    private String formaPagamento;

    @Min(value = 1, message = "O número de parcelas deve ser pelo menos 1")
    @Max(value = 15, message = "O número de parcelas não pode exceder 15")
    private int parcelas;
}
