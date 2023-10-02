package collectiva.org.collecta.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
public class FinanceiroCampanhaDTO {
    private UUID id;

    @NotNull(message = "O valor atingido esta vazio")
    @DecimalMin(value = "0.1", message = "O valor atingido deve ser maior que zero")
    private BigDecimal valorAtingido;

    @NotNull(message = "O valor meta esta vazio")
    @DecimalMin(value = "0.1", message = "O valor meta deve ser maior que zero")
    private BigDecimal valorMeta;


    @NotNull(message = "O modo de contribuição não pode ser nulo")
    @Pattern(regexp = "^(NAO_INICIADO|CONCLUIDO|EM_ANDAMENTO)$", message = "O status da meta deve ser 'EM_ANDAMENTO', 'CONCLUIDO' ou 'NAO_INICIADO'")
    private String metaStatus;
}
