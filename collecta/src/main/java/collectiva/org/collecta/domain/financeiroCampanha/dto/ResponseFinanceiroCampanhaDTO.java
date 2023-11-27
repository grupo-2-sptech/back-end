package collectiva.org.collecta.domain.financeiroCampanha.dto;

import collectiva.org.collecta.domain.campanha.dto.AssociationCampanhaDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.dto.AssociationContribuicaoMonetariaDTO;
import collectiva.org.collecta.enums.MetaStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ResponseFinanceiroCampanhaDTO {
    private UUID id;
    private BigDecimal valorAtingido;
    private BigDecimal valorMeta;
    private MetaStatus metaStatus;
    private AssociationCampanhaDTO campanha;
    private List<AssociationContribuicaoMonetariaDTO> contribuicoes;
}
