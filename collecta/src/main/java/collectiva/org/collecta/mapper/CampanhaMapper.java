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
                .valorMeta(campanhaDTO.getValorMeta())
                .valorArrecadado(campanhaDTO.getValorArrecadado())
                .dataInicio(campanhaDTO.getDataInicio())
                .dataFim(campanhaDTO.getDataFim())
                .status(campanhaDTO.getStatus())
                .build();
    }

    public static CampanhaDTO paraDTO(Campanha campanha){
        return CampanhaDTO.builder()
                .id(campanha.getId())
                .nome(campanha.getNome())
                .descricao(campanha.getDescricao())
                .valorMeta(campanha.getValorMeta())
                .valorArrecadado(campanha.getValorArrecadado())
                .dataInicio(campanha.getDataInicio())
                .dataFim(campanha.getDataFim())
                .status(campanha.getStatus())
                .build();
    }

}
