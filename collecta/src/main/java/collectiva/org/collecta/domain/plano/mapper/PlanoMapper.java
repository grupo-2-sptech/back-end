package collectiva.org.collecta.domain.plano.mapper;

import collectiva.org.collecta.domain.plano.Plano;
import collectiva.org.collecta.domain.plano.dto.PlanoDTO;

public class PlanoMapper {
    private PlanoMapper(){}

    public static Plano paraEntidade(PlanoDTO planoDTO){
        return Plano.builder()
                .dataInicio(planoDTO.getDataInicio())
                .dataFim(planoDTO.getDataFim())
                .tipoPlano(planoDTO.getTipoPlano())
                .statusPlano(planoDTO.getStatusPlano())
                .build();
    }

    public static PlanoDTO paraDTO(Plano plano){
        return PlanoDTO.builder()
                .id(plano.getId())
                .dataInicio(plano.getDataInicio())
                .dataFim(plano.getDataFim())
                .tipoPlano(plano.getTipoPlano())
                .statusPlano(plano.getStatusPlano())
                .build();
    }

}
