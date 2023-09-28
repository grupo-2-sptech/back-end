package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.EventoCampanhaDTO;
import collectiva.org.collecta.service.EventoCampanhaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/eventos")
public class EventoCampanhaController {

    @Autowired
    private EventoCampanhaService eventoCampanhaService;

    @GetMapping
    public ResponseEntity<List<EventoCampanhaDTO>> buscarEventosCampanha() {
        return eventoCampanhaService.buscarTodosEventosCampanha();
    }
    @GetMapping("/{id}")
    public ResponseEntity<EventoCampanhaDTO> buscarEventoCampanhaPorId(@PathVariable UUID id) {
        return eventoCampanhaService.buscarEventoCampanhaPorId(id);
    }

    @PostMapping
    public ResponseEntity<EventoCampanhaDTO> criarEventoCampanha(@RequestBody @Valid EventoCampanhaDTO eventoCampanha) {
        return eventoCampanhaService.salvarEventoCampanha(eventoCampanha);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EventoCampanhaDTO> atualizarEventoCampanha(@PathVariable UUID id, @Valid @RequestBody EventoCampanhaDTO eventoCampanhaDTO) {
        return eventoCampanhaService.atualizarEventoCampanha(id, eventoCampanhaDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEventoCampanha(@PathVariable UUID id){
        return  eventoCampanhaService.deletarEventoCampanha(id);
    }
}