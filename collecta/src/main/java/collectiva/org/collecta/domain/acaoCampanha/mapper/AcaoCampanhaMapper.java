package collectiva.org.collecta.domain.acaoCampanha.mapper;

import collectiva.org.collecta.domain.acaoCampanha.AcaoCampanha;
import collectiva.org.collecta.domain.acaoCampanha.dto.AssociationAcaoCampanhaDTO;
import collectiva.org.collecta.domain.acaoCampanha.dto.CreateAcaoCampanhaDTO;
import collectiva.org.collecta.domain.acaoCampanha.dto.ResponseAcaoCampanhaDTO;
import collectiva.org.collecta.domain.acaoCampanha.dto.UpdateAcaoCampanhaDTO;
import collectiva.org.collecta.domain.relatorio.mapper.RelatorioMapper;

public class AcaoCampanhaMapper {
    private AcaoCampanhaMapper() {
    }

    public static AcaoCampanha paraEntidade(CreateAcaoCampanhaDTO acoesDTO) {
        return AcaoCampanha.builder()
                .nome(acoesDTO.getNome())
                .descricao(acoesDTO.getDescricao())
                .data(acoesDTO.getData())
                .valor(acoesDTO.getValor())
                .build();
    }

    public static AcaoCampanha paraEntidadeUpdate(UpdateAcaoCampanhaDTO acoesDTO) {
        return AcaoCampanha.builder()
                .nome(acoesDTO.getNome())
                .descricao(acoesDTO.getDescricao())
                .data(acoesDTO.getData())
                .valor(acoesDTO.getValor())
                .build();
    }

    public static ResponseAcaoCampanhaDTO paraDTO(AcaoCampanha acoes) {
        return ResponseAcaoCampanhaDTO.builder()
                .id(acoes.getId())
                .nome(acoes.getNome())
                .descricao(acoes.getDescricao())
                .data(acoes.getData())
                .valor(acoes.getValor())
                .relatorio(RelatorioMapper.paraAssociacaoDTO(acoes.getRelatorio()))
                .build();
    }

    public static AssociationAcaoCampanhaDTO paraAssociacaoDTO(AcaoCampanha acoes) {
        return AssociationAcaoCampanhaDTO.builder()
                .id(acoes.getId())
                .nome(acoes.getNome())
                .descricao(acoes.getDescricao())
                .data(acoes.getData())
                .valor(acoes.getValor())
                .build();
    }

}
