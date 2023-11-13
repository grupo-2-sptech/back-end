package collectiva.org.collecta.domain.eventoCampanha.service;

import collectiva.org.collecta.domain.eventoCampanha.EventoCampanha;
import collectiva.org.collecta.domain.eventoCampanha.repository.EventoCampanhaRepository;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventoCampanhaService {
    private final EventoCampanhaRepository eventoCampanhaRepository;

    public EventoCampanha salvarEventoCampanha(EventoCampanha eventoCampanha) {
        return eventoCampanhaRepository.save(eventoCampanha);
    }

    public List<EventoCampanha> buscarTodosEventosCampanha() {
        return eventoCampanhaRepository.findAll();
    }

    public EventoCampanha buscarEventoCampanhaPorId(UUID id) {
        return eventoCampanhaRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Evento"));
    }

    public EventoCampanha atualizarEventoCampanha(UUID id, EventoCampanha eventoCampanha) {
        buscarEventoCampanhaPorId(id);
        eventoCampanha.setId(id);
        return eventoCampanhaRepository.save(eventoCampanha);
    }

    public void deletarEventoCampanha(UUID id) {
        if (!eventoCampanhaRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Evento");
        }
        eventoCampanhaRepository.deleteById(id);
    }
}

