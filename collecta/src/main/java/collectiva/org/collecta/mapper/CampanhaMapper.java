package collectiva.org.collecta.mapper;

import collectiva.org.collecta.domain.Campanha;
import collectiva.org.collecta.dto.CampanhaDTO;

public class CampanhaMapper {

    private CampanhaMapper() {
    }

    public static Campanha paraEntidade(CampanhaDTO campanhaDTO){
        return Campanha.builder()
                .id(campanhaDTO.getId())
                .nome(campanhaDTO.getNome())
                .descricao(campanhaDTO.getDescricao())
                .dataInicio(campanhaDTO.getDataInicio())
                .dataFim(campanhaDTO.getDataFim())
                .categoriaCampanha(campanhaDTO.getCategoriaCampanha())
                .statusCampanha(campanhaDTO.getStatusCampanha())
                .tipoCampanha(campanhaDTO.getTipoCampanha())
                .build();
    }

    public static CampanhaDTO paraDTO(Campanha campanha){
        return CampanhaDTO.builder()
                .id(campanha.getId())
                .nome(campanha.getNome())
                .descricao(campanha.getDescricao())
                .dataInicio(campanha.getDataInicio())
                .dataFim(campanha.getDataFim())
                .categoriaCampanha(campanha.getCategoriaCampanha())
                .statusCampanha(campanha.getStatusCampanha())
                .tipoCampanha(campanha.getTipoCampanha())
                .build();
    }

}
