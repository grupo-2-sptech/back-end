package collectiva.org.collecta.domain.eventoCampanha.service;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.campanha.service.CampanhaService;
import collectiva.org.collecta.domain.eventoCampanha.EventoCampanha;
import collectiva.org.collecta.domain.eventoCampanha.dto.AssociationEventoCampanhaDTO;
import collectiva.org.collecta.domain.eventoCampanha.dto.CreateEventoCampanhaDTO;
import collectiva.org.collecta.domain.eventoCampanha.dto.ResponseEventoCampanhaDTO;
import collectiva.org.collecta.domain.eventoCampanha.dto.UpdateEventoCampanhaDTO;
import collectiva.org.collecta.domain.eventoCampanha.mapper.EventoCampanhaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventoCampanhaServiceFacade {
    private final EventoCampanhaService eventoCampanhaService;
    private final CampanhaService campanhaService;

    public List<ResponseEventoCampanhaDTO> buscarTodosEventoCampanhas() {
        return eventoCampanhaService.buscarTodosEventosCampanha().stream().map
                (EventoCampanhaMapper::paraDTO).toList();
    }

    public ResponseEventoCampanhaDTO buscarEventoCampanhaPorId(UUID id) {
        return EventoCampanhaMapper.paraDTO(eventoCampanhaService.buscarEventoCampanhaPorId(id));
    }

    public List<ResponseEventoCampanhaDTO> buscarEventoCampanhaPorIdCampanha(UUID id) {
        return eventoCampanhaService.buscarEventoCampanhaPorIdCampanha(id).stream().map(EventoCampanhaMapper::paraDTO).toList();
    }

    public AssociationEventoCampanhaDTO criarEventoCampanha(UUID idCampanha, CreateEventoCampanhaDTO eventoCampanhaDTO) {
        Campanha campanha = campanhaService.buscarCampanhaPorId(idCampanha);
        EventoCampanha eventoCampanha = EventoCampanhaMapper.paraEntidade(eventoCampanhaDTO);
        return EventoCampanhaMapper.paraAssociacaoDTO(eventoCampanhaService.criarEventoCampanha(eventoCampanha, campanha));
    }

    public AssociationEventoCampanhaDTO atualizarEventoCampanha(UUID idEventoCampanha, UpdateEventoCampanhaDTO eventoCampanhaDTO) {
        EventoCampanha eventoCampanha = EventoCampanhaMapper.paraEntidadeUpdate(eventoCampanhaDTO);
        return EventoCampanhaMapper.paraAssociacaoDTO(eventoCampanhaService.atualizarEventoCampanha(idEventoCampanha, eventoCampanha));
    }

    public void deletarEventoCampanha(UUID id) {
        eventoCampanhaService.deletarEventoCampanha(id);
    }

}
