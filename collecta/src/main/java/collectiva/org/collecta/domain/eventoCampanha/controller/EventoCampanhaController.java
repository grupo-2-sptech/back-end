package collectiva.org.collecta.domain.eventoCampanha.controller;

import collectiva.org.collecta.domain.eventoCampanha.dto.EventoCampanhaDTO;
import collectiva.org.collecta.domain.eventoCampanha.service.EventoCampanhaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventoCampanhaController {
    private final EventoCampanhaService eventoCampanhaService;

    @GetMapping
    public ResponseEntity<List<EventoCampanhaDTO>> buscarEventosCampanha() {
        List<EventoCampanhaDTO> lista = eventoCampanhaService.buscarTodosEventosCampanha();
        return ResponseEntity.status(lista.isEmpty() ? 204 : 200).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoCampanhaDTO> buscarEventoCampanhaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(eventoCampanhaService.buscarEventoCampanhaPorId(id));
    }

    @PostMapping
    public ResponseEntity<EventoCampanhaDTO> criarEventoCampanha(@RequestBody @Valid EventoCampanhaDTO eventoCampanha) {
        return ResponseEntity.status(201).body(eventoCampanhaService.salvarEventoCampanha(eventoCampanha));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoCampanhaDTO> atualizarEventoCampanha(@PathVariable UUID id, @Valid @RequestBody EventoCampanhaDTO eventoCampanhaDTO) {
        return ResponseEntity.ok(eventoCampanhaService.atualizarEventoCampanha(id, eventoCampanhaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEventoCampanha(@PathVariable UUID id) {
        eventoCampanhaService.deletarEventoCampanha(id);
        return ResponseEntity.noContent().build();
    }
}
