package collectiva.org.collecta.domain.eventoCampanha.controller;

import collectiva.org.collecta.domain.eventoCampanha.EventoCampanha;
import collectiva.org.collecta.domain.eventoCampanha.dto.EventoCampanhaDTO;
import collectiva.org.collecta.domain.eventoCampanha.mapper.EventoCampanhaMapper;
import collectiva.org.collecta.domain.eventoCampanha.service.EventoCampanhaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventoCampanhaController {
    private final EventoCampanhaService eventoCampanhaService;

    @GetMapping
    public ResponseEntity<List<EventoCampanhaDTO>> buscarEventosCampanha() {
        List<EventoCampanhaDTO> listaDTO = eventoCampanhaService.buscarTodosEventosCampanha().stream()
                .map(EventoCampanhaMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoCampanhaDTO> buscarEventoCampanhaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(EventoCampanhaMapper.paraDTO(eventoCampanhaService.buscarEventoCampanhaPorId(id)));
    }

    @PostMapping
    public ResponseEntity<EventoCampanhaDTO> criarEventoCampanha(@RequestBody @Valid EventoCampanhaDTO eventoCampanhaDTO) {
        EventoCampanha eventoCampanha = eventoCampanhaService.salvarEventoCampanha(EventoCampanhaMapper.paraEntidade(eventoCampanhaDTO));
        return ResponseEntity.status(201).body(EventoCampanhaMapper.paraDTO(eventoCampanha));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoCampanhaDTO> atualizarEventoCampanha(@PathVariable UUID id, @Valid @RequestBody EventoCampanhaDTO eventoCampanhaDTO) {
        EventoCampanha eventoCampanha = eventoCampanhaService.atualizarEventoCampanha(id, EventoCampanhaMapper.paraEntidade(eventoCampanhaDTO));
        return ResponseEntity.ok(EventoCampanhaMapper.paraDTO(eventoCampanha));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEventoCampanha(@PathVariable UUID id) {
        eventoCampanhaService.deletarEventoCampanha(id);
        return ResponseEntity.noContent().build();
    }
}
