package collectiva.org.collecta.domain.recurso.controller;

import collectiva.org.collecta.domain.recurso.Recurso;
import collectiva.org.collecta.domain.recurso.dto.RecursoDTO;
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

    @GetMapping
    public ResponseEntity<List<RecursoDTO>> buscarRecursos() {
        List<RecursoDTO> listaDTO = recursoService.buscarTodosRecursos().stream()
                .map(RecursoMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecursoDTO> buscarRecursoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(RecursoMapper.paraDTO(recursoService.buscarRecursoPorId(id)));
    }

    @PostMapping
    public ResponseEntity<RecursoDTO> criarRecurso(@RequestBody @Valid RecursoDTO recursoDTO) {
        Recurso recurso = recursoService.salvarRecurso(RecursoMapper.paraEntidade(recursoDTO));
        return ResponseEntity.status(201).body(RecursoMapper.paraDTO(recurso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecursoDTO> atualizarRecurso(@PathVariable UUID id, @RequestBody @Valid RecursoDTO recursoDTO) {
        Recurso recurso = recursoService.atualizarRecurso(id, RecursoMapper.paraEntidade(recursoDTO));
        return ResponseEntity.ok(RecursoMapper.paraDTO(recurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRecurso(@PathVariable UUID id) {
        recursoService.deletarRecurso(id);
        return ResponseEntity.noContent().build();
    }
}
