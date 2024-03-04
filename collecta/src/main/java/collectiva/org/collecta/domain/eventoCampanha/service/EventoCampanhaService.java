package collectiva.org.collecta.domain.eventoCampanha.service;

import collectiva.org.collecta.domain.campanha.Campanha;
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

    public List<EventoCampanha> buscarTodosEventosCampanha() {
        return eventoCampanhaRepository.findAll();
    }

    public EventoCampanha buscarEventoCampanhaPorId(UUID id) {
        return eventoCampanhaRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Evento"));
    }

    public List<EventoCampanha> buscarEventoCampanhaPorIdCampanha(UUID id) {
        return eventoCampanhaRepository.findByCampanhaId(id);
    }

    public EventoCampanha criarEventoCampanha(EventoCampanha eventoCampanha, Campanha campanha) {
        eventoCampanha.setCampanha(campanha);
        return eventoCampanhaRepository.save(eventoCampanha);
    }

    public EventoCampanha atualizarEventoCampanha(UUID id, EventoCampanha eventoCampanha) {
        EventoCampanha eventoCampanhaAntigo = buscarEventoCampanhaPorId(id);
        eventoCampanha.setId(id);
        eventoCampanha.setCampanha(eventoCampanhaAntigo.getCampanha());
        eventoCampanha.setEnderecos(eventoCampanhaAntigo.getEnderecos());
        eventoCampanha.setContribuicaoServicos(eventoCampanhaAntigo.getContribuicaoServicos());
        return eventoCampanhaRepository.save(eventoCampanha);
    }

    public void deletarEventoCampanha(UUID id) {
        buscarEventoCampanhaPorId(id);
        eventoCampanhaRepository.deleteById(id);
    }

}

