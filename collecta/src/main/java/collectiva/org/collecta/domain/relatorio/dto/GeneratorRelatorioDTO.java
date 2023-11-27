package collectiva.org.collecta.domain.relatorio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneratorRelatorioDTO {
    private BigDecimal valorMeta;
    private BigDecimal valorArrecadado;
    private BigDecimal valorRestante;
    private int visualizacoes;
    private Long contribuicoesMonetarias;
    private Long contribuicoesRecurso;
    private Long contribuicoesServicos;

    public GeneratorRelatorioDTO(BigDecimal valorMeta, BigDecimal valorArrecadado, int visualizacoes, long contribuicoesMonetarias, long contribuicoesRecurso, long contribuicoesServicos) {
        this.valorMeta = valorMeta;
        this.valorArrecadado = valorArrecadado;
        this.valorRestante = valorMeta.subtract(valorArrecadado).max(BigDecimal.ZERO);
        this.visualizacoes = visualizacoes;
        this.contribuicoesMonetarias = contribuicoesMonetarias;
        this.contribuicoesRecurso = contribuicoesRecurso;
        this.contribuicoesServicos = contribuicoesServicos;
    }
}
