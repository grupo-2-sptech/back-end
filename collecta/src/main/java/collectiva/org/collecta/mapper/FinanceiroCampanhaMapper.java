package collectiva.org.collecta.mapper;

import collectiva.org.collecta.domain.FinanceiroCampanha;
import collectiva.org.collecta.domain.enums.MetaStatus;
import collectiva.org.collecta.dto.FinanceiroCampanhaDTO;

import java.time.LocalDateTime;

public class FinanceiroCampanhaMapper {
    private FinanceiroCampanhaMapper() {
    }
    public static FinanceiroCampanha paraEntidade(FinanceiroCampanhaDTO doacaoDTO){
        return FinanceiroCampanha.builder()
                .valorMeta(doacaoDTO.getValorMeta())
                .valorAtingido(doacaoDTO.getValorAtingido())
                .metaStatus(doacaoDTO.getMetaStatus())
                .build();
    }

    public static FinanceiroCampanhaDTO paraDTO(FinanceiroCampanha doacao){
        return FinanceiroCampanhaDTO.builder()
                .id(doacao.getId())
                .valorMeta(doacao.getValorMeta())
                .valorAtingido(doacao.getValorAtingido())
                .metaStatus(doacao.getMetaStatus())
                .build();
    }
}
