package collectiva.org.collecta.dto;

import collectiva.org.collecta.domain.enums.ModoContribuição;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class DoacaoDTO {
    private UUID id;
    @NotNull(message = "O valor arrecadado esta vazio")
    @DecimalMin(value = "0.1", message = "O valor arrecadado deve ser maior que zero")
    private BigDecimal valor;

    private LocalDateTime dataHora;

    @NotNull(message = "O modo de contribuição não pode ser nulo")
    @Pattern(regexp = "^(MENSAL|UNITARIO)$", message = "O modo de contribuição deve ser 'MENSAL' ou 'UNITARIO'")
    private String modoContribuicao;
}
