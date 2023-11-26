package collectiva.org.collecta.domain.campanha.mapper;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.campanha.dto.AssociationCampanhaDTO;
import collectiva.org.collecta.domain.campanha.dto.CreateCampanhaDTO;
import collectiva.org.collecta.domain.campanha.dto.ResponseCampanhaDTO;
import collectiva.org.collecta.domain.campanha.dto.UpdateCampanhaDTO;
import collectiva.org.collecta.domain.conta.organizacao.mapper.OrganizacaoMapper;
import collectiva.org.collecta.domain.eventoCampanha.mapper.EventoCampanhaMapper;
import collectiva.org.collecta.domain.financeiroCampanha.mapper.FinanceiroCampanhaMapper;
import collectiva.org.collecta.domain.recurso.mapper.RecursoMapper;
import collectiva.org.collecta.enums.StatusCampanha;

import java.time.LocalDateTime;
import java.util.Optional;

public class CampanhaMapper {

    private CampanhaMapper() {
    }

    public static Campanha paraEntidade(CreateCampanhaDTO campanhaDTO){
        return Campanha.builder()
                .nome(campanhaDTO.getNome())
                .descricao(campanhaDTO.getDescricao())
                .dataInicio(LocalDateTime.now())
                .dataFim(campanhaDTO.getDataFim())
                .categoriaCampanha(campanhaDTO.getCategoriaCampanha())
                .tipoCampanha(campanhaDTO.getTipoCampanha())
                .statusCampanha(StatusCampanha.ATIVA)
                .build();
    }

    public static Campanha paraEntidadeUpdate(UpdateCampanhaDTO campanhaDTO){
        return Campanha.builder()
                .nome(campanhaDTO.getNome())
                .descricao(campanhaDTO.getDescricao())
                .dataInicio(LocalDateTime.now())
                .dataFim(campanhaDTO.getDataFim())
                .categoriaCampanha(campanhaDTO.getCategoriaCampanha())
                .tipoCampanha(campanhaDTO.getTipoCampanha())
                .statusCampanha(StatusCampanha.ATIVA)

                .build();
    }

    public static ResponseCampanhaDTO paraDTO(Campanha campanha){
        return ResponseCampanhaDTO.builder()
                .id(campanha.getId())
                .nome(campanha.getNome())
                .descricao(campanha.getDescricao())
                .dataInicio(campanha.getDataInicio())
                .dataFim(campanha.getDataFim())
                .categoriaCampanha(campanha.getCategoriaCampanha())
                .statusCampanha(campanha.getStatusCampanha())
                .tipoCampanha(campanha.getTipoCampanha())
                .visulizacoes(campanha.getVisualizacoes())
                .organizacao(OrganizacaoMapper.paraAssociacaoDTO(campanha.getOrganizacao()))
                .financeiroCampanha(Optional.ofNullable(campanha.getFinanceirosCampanha())
                        .map(FinanceiroCampanhaMapper::paraAssociacaoDTO)
                        .orElse(null))
                .recursos(campanha.getRecursos().stream().map(RecursoMapper::paraAssociacaoDTO).toList())
                .eventosCampanha(campanha.getEventoCampanhas().stream().map(EventoCampanhaMapper::paraAssociacaoDTO).toList())
                .build();
    }

    public static AssociationCampanhaDTO paraAssociacaoDTO(Campanha campanha){
        return AssociationCampanhaDTO.builder()
                .id(campanha.getId())
                .nome(campanha.getNome())
                .descricao(campanha.getDescricao())
                .dataInicio(campanha.getDataInicio())
                .dataFim(campanha.getDataFim())
                .categoriaCampanha(campanha.getCategoriaCampanha())
                .statusCampanha(campanha.getStatusCampanha())
                .tipoCampanha(campanha.getTipoCampanha())
                .visulizacoes(campanha.getVisualizacoes())
                .build();
    }

}
