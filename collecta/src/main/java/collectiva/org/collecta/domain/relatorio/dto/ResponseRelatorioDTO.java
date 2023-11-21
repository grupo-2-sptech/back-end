package collectiva.org.collecta.domain.relatorio.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ResponseRelatorioDTO {
    private UUID id;
    private LocalDateTime data;
    private BigDecimal valorArrecadado;
    private int quantidadeFinanceirosCampanha;
}
