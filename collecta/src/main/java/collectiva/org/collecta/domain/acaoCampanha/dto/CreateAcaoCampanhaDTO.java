package collectiva.org.collecta.domain.acaoCampanha.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class CreateAcaoCampanhaDTO {
    @NotBlank(message = "O nome está vazio")
    @Size(min = 3, max = 255, message = "O nome deve ter no mínimo 3 e no máximo 255 caracteres")
    private String nome;

    @NotBlank(message = "A descrição está vazio")
    @Size(min = 5, max = 700, message = "A descrição deve ter no mínimo 5 e no máximo 700 caracteres")
    private String descricao;

    @NotNull(message = "A data está vazio")
    @PastOrPresent(message = "A data deve estar no passado ou presente")
    private LocalDate data;

    @DecimalMin(value = "0.00", message = "O valor deve ser maior ou igual a 0.00")
    private BigDecimal valor;
}
