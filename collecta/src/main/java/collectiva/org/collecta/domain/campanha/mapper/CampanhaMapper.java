package collectiva.org.collecta.domain.campanha.mapper;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.campanha.dto.CreateCampanhaDTO;
import collectiva.org.collecta.domain.campanha.dto.ResponseCampanhaDTO;

public class CampanhaMapper {

    private CampanhaMapper() {
    }

    public static Campanha paraEntidade(CreateCampanhaDTO campanhaDTO){
        return Campanha.builder()
                .nome(campanhaDTO.getNome())
                .descricao(campanhaDTO.getDescricao())
                .dataInicio(campanhaDTO.getDataInicio())
                .dataFim(campanhaDTO.getDataFim())
                .categoriaCampanha(campanhaDTO.getCategoriaCampanha())
                .tipoCampanha(campanhaDTO.getTipoCampanha())
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
                .build();
    }

}
