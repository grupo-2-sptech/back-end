package collectiva.org.collecta.domain.pagamento.dto;

import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.dto.AssociationContribuicaoMonetariaDTO;
import collectiva.org.collecta.integration.pix.location.json.PixGenerateQRCode;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ResponsePagamentoDTO {
    private UUID id;
    private String nomeTitular;
    private String cpf;
    private String valor;
    private String numeroCartao;
    private String dataValidade;
    private String codigoSeguranca;
    private String bandeiraCartao;
    private AssociationContribuicaoMonetariaDTO contribuicaoMonetaria;



}
