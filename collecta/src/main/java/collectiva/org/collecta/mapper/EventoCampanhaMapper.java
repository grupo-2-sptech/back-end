package collectiva.org.collecta.mapper;

import collectiva.org.collecta.domain.Campanha;
import collectiva.org.collecta.domain.EventoCampanha;
import collectiva.org.collecta.dto.CampanhaDTO;
import collectiva.org.collecta.dto.EventoCampanhaDTO;

public class EventoCampanhaMapper {

    private EventoCampanhaMapper() {
    }

    public static EventoCampanha paraEntidade(EventoCampanhaDTO eventoCampanhaDTO){
        return EventoCampanha.builder()
                .nome(eventoCampanhaDTO.getNome())
                .descricao(eventoCampanhaDTO.getDescricao())
                .dataHora(eventoCampanhaDTO.getDataHora())
                .build();
    }

    public static EventoCampanhaDTO paraDTO(EventoCampanha eventoCampanha){
        return EventoCampanhaDTO.builder()
                .id(eventoCampanha.getId())
                .nome(eventoCampanha.getNome())
                .descricao(eventoCampanha.getDescricao())
                .dataHora(eventoCampanha.getDataHora())
                .build();
    }

}
