package collectiva.org.collecta.domain.relatorio.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class CreateRelatorioDTO {
    private BigDecimal valorMeta;
    private BigDecimal valorArrecadado;
    private BigDecimal valorRestante;
    private int visualizacoes;
    private Long contribuicoesMonetarias;
    private Long contribuicoesRecurso;
    private Long contribuicoesServicos;
    private LocalDateTime data;
}
