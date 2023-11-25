package collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.dto;

import collectiva.org.collecta.domain.conta.doador.dto.AssociationDoadorDTO;
import collectiva.org.collecta.domain.financeiroCampanha.dto.AssociationFinanceiroCampanhaDTO;
import collectiva.org.collecta.domain.pagamento.dto.AssociationPagamentoDTO;
import collectiva.org.collecta.enums.FormaPagamento;
import collectiva.org.collecta.enums.StatusContribuicao;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ResponseContribuicaoMonetariaDTO {
    private UUID id;
    private LocalDateTime dataHora;
    private BigDecimal valor;
    private Integer parcelas;
    private FormaPagamento formaPagamento;
    private StatusContribuicao statusContribuicao;
    private AssociationDoadorDTO doador;
    private AssociationFinanceiroCampanhaDTO financeiroCampanha;
    private AssociationPagamentoDTO pagamento;

}
