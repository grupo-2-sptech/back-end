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
    private BigDecimal valorMeta;
    private BigDecimal valorArrecadado;
    private BigDecimal valorRestante;
    private int visualizacoes;
    private Long contribuicoesMonetarias;
    private Long contribuicoesRecurso;
    private Long contribuicoesServicos;
    private LocalDateTime data;
}
