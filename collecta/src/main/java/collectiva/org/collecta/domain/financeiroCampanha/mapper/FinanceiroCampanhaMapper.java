package collectiva.org.collecta.domain.financeiroCampanha.mapper;

import collectiva.org.collecta.domain.campanha.mapper.CampanhaMapper;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.ContribuicaoMonetaria;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.mapper.ContribuicaoMonetariaMapper;
import collectiva.org.collecta.domain.financeiroCampanha.FinanceiroCampanha;
import collectiva.org.collecta.domain.financeiroCampanha.dto.AssociationFinanceiroCampanhaDTO;
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
                .campanha(CampanhaMapper.paraAssociacaoDTO(doacao.getCampanha()))
                .contribuicoes(doacao.getContribuicaoMonetarias().stream().map(ContribuicaoMonetariaMapper::paraAssociacaoDTO).toList())
                .build();
    }

    public static AssociationFinanceiroCampanhaDTO paraAssociacaoDTO(FinanceiroCampanha doacao){
        return AssociationFinanceiroCampanhaDTO.builder()
                .id(doacao.getId())
                .valorMeta(doacao.getValorMeta())
                .valorAtingido(doacao.getValorAtingido())
                .metaStatus(doacao.getMetaStatus())
                .build();
    }
}
