package collectiva.org.collecta.domain.relatorio.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CreateRelatorioDTO {
    private BigDecimal valorMeta;
    private BigDecimal valorArrecadado;
    private BigDecimal valorRestante;
    private int visualizacoes;
    private int contribuicoesMonetarias;
    private int contribuicoesRecurso;
    private int contribuicoesServicos;
    private LocalDateTime data;
    private UUID idCampanha;
}
