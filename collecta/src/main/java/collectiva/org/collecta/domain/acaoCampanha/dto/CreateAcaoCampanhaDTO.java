package collectiva.org.collecta.domain.acaoCampanha.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class CreateAcaoCampanhaDTO {
    @NotBlank(message = "O nome está vazio")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
    private String nome;

    @NotBlank(message = "A descrição está vazio")
    @Size(min = 5, message = "A descrição deve ter no mínimo 5 caracteres")
    private String descricao;

    @NotNull(message = "A data está vazio")
    @Past(message = "A data deve estar no passado")
    private LocalDate data;

    @DecimalMin(value = "0.00", message = "O valor deve ser maior ou igual a 0.00")
    private BigDecimal valor;

    @NotNull(message = "O id do relatório está vazio")
    private UUID idRelatorio;
}
