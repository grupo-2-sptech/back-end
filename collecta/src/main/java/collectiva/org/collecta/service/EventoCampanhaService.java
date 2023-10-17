package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.EventoCampanha;
import collectiva.org.collecta.dto.EventoCampanhaDTO;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import collectiva.org.collecta.mapper.EventoCampanhaMapper;
import collectiva.org.collecta.repository.EventoCampanhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventoCampanhaService {
    private final EventoCampanhaRepository eventoCampanhaRepository;

    public EventoCampanhaDTO salvarEventoCampanha(EventoCampanhaDTO eventoCampanhaDTO) {
        EventoCampanha eventoCampanha = EventoCampanhaMapper.paraEntidade(eventoCampanhaDTO);
        eventoCampanhaRepository.save(eventoCampanha);
        return EventoCampanhaMapper.paraDTO(eventoCampanha);
    }

    public List<EventoCampanhaDTO> buscarTodosEventosCampanha() {
        List<EventoCampanha> eventoCampanha = eventoCampanhaRepository.findAll();
        return eventoCampanha.stream().map(EventoCampanhaMapper::paraDTO).collect(Collectors.toList());
    }

    public EventoCampanhaDTO buscarEventoCampanhaPorId(UUID id) {
        return EventoCampanhaMapper.paraDTO(eventoCampanhaRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Evento")));
    }

    public EventoCampanhaDTO atualizarEventoCampanha(UUID id, EventoCampanhaDTO eventoCampanhaDTO) {
        buscarEventoCampanhaPorId(id);
        EventoCampanha eventoNovo = EventoCampanhaMapper.paraEntidade(eventoCampanhaDTO);
        eventoNovo.setId(id);
        eventoCampanhaRepository.save(eventoNovo);
        return EventoCampanhaMapper.paraDTO(eventoNovo);
    }

    public void deletarEventoCampanha(UUID id) {
        if (!eventoCampanhaRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Evento");
        }
        eventoCampanhaRepository.deleteById(id);
    }
}

