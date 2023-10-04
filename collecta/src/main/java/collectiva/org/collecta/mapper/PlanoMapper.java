package collectiva.org.collecta.mapper;

import collectiva.org.collecta.domain.Plano;
import collectiva.org.collecta.dto.PlanoDTO;

import java.time.LocalDateTime;

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
