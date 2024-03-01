package collectiva.org.collecta.domain.eventoCampanha.controller;

import collectiva.org.collecta.domain.eventoCampanha.dto.AssociationEventoCampanhaDTO;
import collectiva.org.collecta.domain.eventoCampanha.dto.CreateEventoCampanhaDTO;
import collectiva.org.collecta.domain.eventoCampanha.dto.ResponseEventoCampanhaDTO;
import collectiva.org.collecta.domain.eventoCampanha.dto.UpdateEventoCampanhaDTO;
import collectiva.org.collecta.domain.eventoCampanha.service.EventoCampanhaServiceFacade;
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
    private final EventoCampanhaServiceFacade eventoCampanhaServiceF;

    @GetMapping
    public ResponseEntity<List<ResponseEventoCampanhaDTO>> buscarEventosCampanha() {
        List<ResponseEventoCampanhaDTO> listaDTO = eventoCampanhaServiceF.buscarTodosEventoCampanhas();
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEventoCampanhaDTO> buscarEventoCampanhaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(eventoCampanhaServiceF.buscarEventoCampanhaPorId(id));
    }

    @GetMapping("/campanha/{id}")
    public ResponseEntity<List<ResponseEventoCampanhaDTO>> buscarEventoCampanhaPorIdCampanha(@PathVariable UUID id) {
        List<ResponseEventoCampanhaDTO> listaDTO = eventoCampanhaServiceF.buscarEventoCampanhaPorIdCampanha(id);
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @PostMapping("/{idEventoCampanha}")
    public ResponseEntity<AssociationEventoCampanhaDTO> criarEventoCampanha(@PathVariable UUID idEventoCampanha, @RequestBody @Valid CreateEventoCampanhaDTO eventoCampanhaDTO) {
        return ResponseEntity.status(201).body(eventoCampanhaServiceF.criarEventoCampanha(idEventoCampanha, eventoCampanhaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationEventoCampanhaDTO> atualizarEventoCampanha(@PathVariable UUID id, @Valid @RequestBody UpdateEventoCampanhaDTO eventoCampanhaDTO) {
        return ResponseEntity.ok(eventoCampanhaServiceF.atualizarEventoCampanha(id, eventoCampanhaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEventoCampanha(@PathVariable UUID id) {
        eventoCampanhaServiceF.deletarEventoCampanha(id);
        return ResponseEntity.noContent().build();
    }
}
