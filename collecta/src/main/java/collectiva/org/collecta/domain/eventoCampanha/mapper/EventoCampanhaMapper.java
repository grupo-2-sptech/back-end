package collectiva.org.collecta.domain.eventoCampanha.mapper;

import collectiva.org.collecta.domain.campanha.mapper.CampanhaMapper;
import collectiva.org.collecta.domain.endereco.mapper.EnderecoMapper;
import collectiva.org.collecta.domain.eventoCampanha.EventoCampanha;
import collectiva.org.collecta.domain.eventoCampanha.dto.AssociationEventoCampanhaDTO;
import collectiva.org.collecta.domain.eventoCampanha.dto.CreateEventoCampanhaDTO;
import collectiva.org.collecta.domain.eventoCampanha.dto.ResponseEventoCampanhaDTO;
import collectiva.org.collecta.domain.eventoCampanha.dto.UpdateEventoCampanhaDTO;

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

    public static EventoCampanha paraEntidadeUpdate(UpdateEventoCampanhaDTO eventoCampanhaDTO){
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
                .campanha(CampanhaMapper.paraAssociacaoDTO(eventoCampanha.getCampanha()))
                .enderecos(eventoCampanha.getEnderecos().stream().map(EnderecoMapper::paraAssociacaoDTO).toList())
                .build();
    }

    public static AssociationEventoCampanhaDTO paraAssociacaoDTO(EventoCampanha eventoCampanha){
        return AssociationEventoCampanhaDTO.builder()
                .id(eventoCampanha.getId())
                .nome(eventoCampanha.getNome())
                .descricao(eventoCampanha.getDescricao())
                .dataHora(eventoCampanha.getDataHora())
                .build();
    }

}
