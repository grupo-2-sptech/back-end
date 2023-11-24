package collectiva.org.collecta.domain.plano.mapper;

import collectiva.org.collecta.domain.plano.Plano;
import collectiva.org.collecta.domain.plano.dto.CreatePlanoDTO;
import collectiva.org.collecta.domain.plano.dto.ResponsePlanoDTO;
import collectiva.org.collecta.domain.plano.dto.UpdatePlanoDTO;
import collectiva.org.collecta.enums.StatusPlano;

import java.time.LocalDateTime;

public class PlanoMapper {
    private PlanoMapper(){}

    public static Plano paraEntidade(CreatePlanoDTO planoDTO){
        return Plano.builder()
                .dataInicio(LocalDateTime.now())
                .dataFim(planoDTO.getDataFim())
                .tipoPlano(planoDTO.getTipoPlano())
                .statusPlano(StatusPlano.ATIVO)
                .build();
    }

    public static Plano paraEntidadeUpdate(UpdatePlanoDTO planoDTO){
        return Plano.builder()
                .dataInicio(LocalDateTime.now())
                .dataFim(planoDTO.getDataFim())
                .tipoPlano(planoDTO.getTipoPlano())
                .statusPlano(StatusPlano.ATIVO)
                .build();
    }

    public static ResponsePlanoDTO paraDTO(Plano plano){
        return ResponsePlanoDTO.builder()
                .id(plano.getId())
                .dataInicio(plano.getDataInicio())
                .dataFim(plano.getDataFim())
                .tipoPlano(plano.getTipoPlano())
                .statusPlano(plano.getStatusPlano())
                .build();
    }

}
