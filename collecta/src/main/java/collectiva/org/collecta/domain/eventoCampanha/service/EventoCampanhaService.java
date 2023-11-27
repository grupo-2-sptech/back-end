package collectiva.org.collecta.domain.eventoCampanha.service;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.eventoCampanha.EventoCampanha;
import collectiva.org.collecta.domain.eventoCampanha.repository.EventoCampanhaRepository;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import collectiva.org.collecta.utils.PilhaObj;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class EventoCampanhaService {
    private final EventoCampanhaRepository eventoCampanhaRepository;

    public EventoCampanha salvarEventoCampanha(EventoCampanha eventoCampanha, Campanha campanha) {
        eventoCampanha.setCampanha(campanha);
        return eventoCampanhaRepository.save(eventoCampanha);
    }

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

    public PilhaObj trazEmPilha() {
        List<EventoCampanha> list = eventoCampanhaRepository.findAll();
        PilhaObj pilhaObj = new PilhaObj<>(list.size());

        System.out.println("Tamanho da lista: " + list.size());

        for (EventoCampanha eventoDaVez : list){
            pilhaObj.push(eventoDaVez);
        }
        return pilhaObj;
    }
}

