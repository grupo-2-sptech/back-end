package collectiva.org.collecta.domain.recurso.controller;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.campanha.service.CampanhaService;
import collectiva.org.collecta.domain.recurso.Recurso;
import collectiva.org.collecta.domain.recurso.dto.AssociationRecursoDTO;
import collectiva.org.collecta.domain.recurso.dto.CreateRecursoDTO;
import collectiva.org.collecta.domain.recurso.dto.ResponseRecursoDTO;
import collectiva.org.collecta.domain.recurso.dto.UpdateRecursoDTO;
import collectiva.org.collecta.domain.recurso.mapper.RecursoMapper;
import collectiva.org.collecta.domain.recurso.service.RecursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/recursos")
@RequiredArgsConstructor
public class RecursoController {
    private final RecursoService recursoService;
    private final CampanhaService campanhaService;

    @GetMapping
    public ResponseEntity<List<ResponseRecursoDTO>> buscarRecursos() {
        List<ResponseRecursoDTO> listaDTO = recursoService.buscarTodosRecursos().stream()
                .map(RecursoMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseRecursoDTO> buscarRecursoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(RecursoMapper.paraDTO(recursoService.buscarRecursoPorId(id)));
    }

    @PostMapping
    public ResponseEntity<AssociationRecursoDTO> criarRecurso(@RequestBody @Valid CreateRecursoDTO recursoDTO) {
        Campanha campanha = campanhaService.buscarCampanhaPorId(recursoDTO.getIdCampanha());
        Recurso recurso = recursoService.salvarRecurso(RecursoMapper.paraEntidade(recursoDTO), campanha);
        return ResponseEntity.status(201).body(RecursoMapper.paraAssociacaoDTO(recurso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationRecursoDTO> atualizarRecurso(@PathVariable UUID id, @RequestBody @Valid UpdateRecursoDTO recursoDTO) {
        Recurso recurso = recursoService.atualizarRecurso(id, RecursoMapper.paraEntidadeUpdate(recursoDTO));
        return ResponseEntity.ok(RecursoMapper.paraAssociacaoDTO(recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRecurso(@PathVariable UUID id) {
        recursoService.deletarRecurso(id);
        return ResponseEntity.noContent().build();
    }
}
