package collectiva.org.collecta.domain.relatorio.dto;

import collectiva.org.collecta.domain.acaoCampanha.dto.AssociationAcaoCampanhaDTO;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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
    private List<AssociationAcaoCampanhaDTO> acoes;
}
