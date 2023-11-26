package collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.dto;

import collectiva.org.collecta.domain.conta.doador.dto.AssociationDoadorDTO;
import collectiva.org.collecta.domain.financeiroCampanha.dto.AssociationFinanceiroCampanhaDTO;
import collectiva.org.collecta.domain.pagamento.dto.AssociationPagamentoDTO;
import collectiva.org.collecta.enums.FormaPagamento;
import collectiva.org.collecta.enums.StatusContribuicao;
import collectiva.org.collecta.integration.pix.location.json.PixGenerateQRCode;
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
    private String txid;
    private String idCodigoPix;


    public String getCodigoPix(){
        String codigoPix =  PixGenerateQRCode.GerarCodigoCopiaCola(idCodigoPix).toString();
        return  codigoPix;
    }
    public String getQrCode(){
        String codigoPix =  PixGenerateQRCode.GerarQrCode(idCodigoPix).toString();
        return  codigoPix;
    }
    public String getLinkPagamento(){
        String codigoPix =  PixGenerateQRCode.GerarLinkPagamento(idCodigoPix).toString();
        return  codigoPix;
    }

}
