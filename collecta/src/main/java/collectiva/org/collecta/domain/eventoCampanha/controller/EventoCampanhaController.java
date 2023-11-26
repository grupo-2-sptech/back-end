package collectiva.org.collecta.domain.eventoCampanha.controller;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.campanha.service.CampanhaService;
import collectiva.org.collecta.domain.eventoCampanha.EventoCampanha;
import collectiva.org.collecta.domain.eventoCampanha.dto.AssociationEventoCampanhaDTO;
import collectiva.org.collecta.domain.eventoCampanha.dto.CreateEventoCampanhaDTO;
import collectiva.org.collecta.domain.eventoCampanha.dto.ResponseEventoCampanhaDTO;
import collectiva.org.collecta.domain.eventoCampanha.dto.UpdateEventoCampanhaDTO;
import collectiva.org.collecta.domain.eventoCampanha.mapper.EventoCampanhaMapper;
import collectiva.org.collecta.domain.eventoCampanha.service.EventoCampanhaService;
import collectiva.org.collecta.utils.PilhaObj;
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
    private final CampanhaService campanhaService;

    @GetMapping
    public ResponseEntity<List<ResponseEventoCampanhaDTO>> buscarEventosCampanha() {
        List<ResponseEventoCampanhaDTO> listaDTO = eventoCampanhaService.buscarTodosEventosCampanha().stream()
                .map(EventoCampanhaMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEventoCampanhaDTO> buscarEventoCampanhaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(EventoCampanhaMapper.paraDTO(eventoCampanhaService.buscarEventoCampanhaPorId(id)));
    }

    @GetMapping("/campanha/{id}")
    public ResponseEntity<List<ResponseEventoCampanhaDTO>> buscarEventoCampanhaPorIdCampanha(@PathVariable UUID id) {
        List<ResponseEventoCampanhaDTO> listaDTO = eventoCampanhaService.buscarEventoCampanhaPorIdCampanha(id).stream()
                .map(EventoCampanhaMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/pilha")
    public ResponseEntity<PilhaObj> buscarEmPilha(){
        PilhaObj pilhaObj = eventoCampanhaService.trazEmPilha();
        return ResponseEntity.status(pilhaObj.isEmpty() ? 204 : 200).body(pilhaObj);
    }

    @PostMapping
    public ResponseEntity<AssociationEventoCampanhaDTO> criarEventoCampanha(@RequestBody @Valid CreateEventoCampanhaDTO eventoCampanhaDTO) {
        Campanha campanha = campanhaService.buscarCampanhaPorId(eventoCampanhaDTO.getIdCampanha());
        EventoCampanha eventoCampanha = eventoCampanhaService.salvarEventoCampanha(EventoCampanhaMapper.paraEntidade(eventoCampanhaDTO), campanha );
        return ResponseEntity.status(201).body(EventoCampanhaMapper.paraAssociacaoDTO(eventoCampanha));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationEventoCampanhaDTO> atualizarEventoCampanha(@PathVariable UUID id, @Valid @RequestBody UpdateEventoCampanhaDTO eventoCampanhaDTO) {
        EventoCampanha eventoCampanha = eventoCampanhaService.atualizarEventoCampanha(id, EventoCampanhaMapper.paraEntidadeUpdate(eventoCampanhaDTO));
        return ResponseEntity.ok(EventoCampanhaMapper.paraAssociacaoDTO(eventoCampanha));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEventoCampanha(@PathVariable UUID id) {
        eventoCampanhaService.deletarEventoCampanha(id);
        return ResponseEntity.noContent().build();
    }
}
