package collectiva.org.collecta.domain.eventoCampanha.mapper;

import collectiva.org.collecta.domain.eventoCampanha.EventoCampanha;
import collectiva.org.collecta.domain.eventoCampanha.dto.CreateEventoCampanhaDTO;
import collectiva.org.collecta.domain.eventoCampanha.dto.ResponseEventoCampanhaDTO;

public class EventoCampanhaMapper {

    private EventoCampanhaMapper() {
    }

    public static EventoCampanha paraEntidade(CreateEventoCampanhaDTO eventoCampanhaDTO){
        return EventoCampanha.builder()
                .nome(eventoCampanhaDTO.getNome())
                .descricao(eventoCampanhaDTO.getDescricao())
                .dataHora(eventoCampanhaDTO.getDataHora())
                .build();
    }

    public static ResponseEventoCampanhaDTO paraDTO(EventoCampanha eventoCampanha){
        return ResponseEventoCampanhaDTO.builder()
                .id(eventoCampanha.getId())
                .nome(eventoCampanha.getNome())
                .descricao(eventoCampanha.getDescricao())
                .dataHora(eventoCampanha.getDataHora())
                .build();
    }

}
