package collectiva.org.collecta.domain.financeiroCampanha.mapper;

import collectiva.org.collecta.domain.financeiroCampanha.FinanceiroCampanha;
import collectiva.org.collecta.domain.financeiroCampanha.dto.CreateFinanceiroCampanhaDTO;
import collectiva.org.collecta.domain.financeiroCampanha.dto.ResponseFinanceiroCampanhaDTO;
import collectiva.org.collecta.domain.financeiroCampanha.dto.UpdateFinanceiroCampanhaDTO;
import collectiva.org.collecta.enums.MetaStatus;

import java.math.BigDecimal;

public class FinanceiroCampanhaMapper {
    private FinanceiroCampanhaMapper() {
    }
    public static FinanceiroCampanha paraEntidade(CreateFinanceiroCampanhaDTO doacaoDTO){
        return FinanceiroCampanha.builder()
                .valorMeta(doacaoDTO.getValorMeta())
                .valorAtingido(BigDecimal.valueOf(0.0))
                .metaStatus(MetaStatus.INICIADO)
                .build();
    }

    public static FinanceiroCampanha paraEntidadeUpdate(UpdateFinanceiroCampanhaDTO doacaoDTO){
        return FinanceiroCampanha.builder()
                .valorAtingido(BigDecimal.valueOf(0.0))
                .metaStatus(doacaoDTO.getMetaStatus())
                .build();
    }

    public static ResponseFinanceiroCampanhaDTO paraDTO(FinanceiroCampanha doacao){
        return ResponseFinanceiroCampanhaDTO.builder()
                .id(doacao.getId())
                .valorMeta(doacao.getValorMeta())
                .valorAtingido(doacao.getValorAtingido())
                .metaStatus(doacao.getMetaStatus())
                .build();
    }
}
