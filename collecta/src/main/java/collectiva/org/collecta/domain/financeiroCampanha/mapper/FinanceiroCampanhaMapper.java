package collectiva.org.collecta.domain.financeiroCampanha.mapper;

import collectiva.org.collecta.domain.financeiroCampanha.FinanceiroCampanha;
import collectiva.org.collecta.domain.financeiroCampanha.dto.CreateFinanceiroCampanhaDTO;
import collectiva.org.collecta.domain.financeiroCampanha.dto.ResponseFinanceiroCampanhaDTO;

public class FinanceiroCampanhaMapper {
    private FinanceiroCampanhaMapper() {
    }
    public static FinanceiroCampanha paraEntidade(CreateFinanceiroCampanhaDTO doacaoDTO){
        return FinanceiroCampanha.builder()
                .valorMeta(doacaoDTO.getValorMeta())
                .valorAtingido(doacaoDTO.getValorAtingido())
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
