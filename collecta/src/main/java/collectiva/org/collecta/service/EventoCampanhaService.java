package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.EventoCampanha;
import collectiva.org.collecta.dto.EventoCampanhaDTO;
import collectiva.org.collecta.mapper.EventoCampanhaMapper;
import collectiva.org.collecta.repository.EventoCampanhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventoCampanhaService {
    private final EventoCampanhaRepository eventoCampanhaRepository;

    public ResponseEntity<EventoCampanhaDTO> salvarEventoCampanha(EventoCampanhaDTO eventoCampanhaDTO) {
        EventoCampanha eventoCampanha = EventoCampanhaMapper.paraEntidade(eventoCampanhaDTO);
        eventoCampanhaRepository.save(eventoCampanha);
        eventoCampanhaDTO = EventoCampanhaMapper.paraDTO(eventoCampanha);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoCampanhaDTO);
    }

    public ResponseEntity<List<EventoCampanhaDTO>> buscarTodosEventosCampanha() {
        List<EventoCampanha> eventoCampanha = eventoCampanhaRepository.findAll();
        if (eventoCampanha.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<EventoCampanhaDTO> eventoCampanhaDTOs = eventoCampanha.stream().map(EventoCampanhaMapper::paraDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(eventoCampanhaDTOs);
    }

    public ResponseEntity<EventoCampanhaDTO> buscarEventoCampanhaPorId(UUID id) {
        Optional<EventoCampanha> eventoCampanha = eventoCampanhaRepository.findById(id);
        if (eventoCampanha.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        EventoCampanhaDTO eventoCampanhaDTO = EventoCampanhaMapper.paraDTO(eventoCampanha.get());
        return ResponseEntity.ok().body(eventoCampanhaDTO);
    }

    public ResponseEntity<EventoCampanhaDTO> atualizarEventoCampanha(UUID id, EventoCampanhaDTO eventoCampanhaDTO) {
        Optional<EventoCampanha> eventoCampanhaAntiga = eventoCampanhaRepository.findById(id);
        if (eventoCampanhaAntiga.isPresent()) {
            EventoCampanha eventoCampanha = EventoCampanhaMapper.paraEntidade(eventoCampanhaDTO);
            eventoCampanha.setId(eventoCampanhaAntiga.get().getId());
            eventoCampanhaDTO.setId(eventoCampanhaAntiga.get().getId());

            eventoCampanhaRepository.save(eventoCampanha);
            return ResponseEntity.ok().body(eventoCampanhaDTO);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> deletarEventoCampanha(UUID id) {
        if (!eventoCampanhaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        eventoCampanhaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

