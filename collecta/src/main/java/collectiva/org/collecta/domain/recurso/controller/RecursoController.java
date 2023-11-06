package collectiva.org.collecta.domain.recurso.controller;

import collectiva.org.collecta.domain.recurso.dto.RecursoDTO;
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
        List<RecursoDTO> lista = recursoService.buscarTodosRecursos();
        return ResponseEntity.status(lista.isEmpty() ? 204 : 200).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecursoDTO> buscarRecursoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(recursoService.buscarRecursoPorId(id));
    }

    @PostMapping
    public ResponseEntity<RecursoDTO> criarRecurso(@RequestBody @Valid RecursoDTO recursoDTO) {
        return ResponseEntity.status(201).body(recursoService.salvarRecurso(recursoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecursoDTO> atualizarRecurso(@PathVariable UUID id, @RequestBody @Valid RecursoDTO recursoDTO) {
        return ResponseEntity.ok(recursoService.atualizarRecurso(id, recursoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRecurso(@PathVariable UUID id) {
        recursoService.deletarRecurso(id);
        return ResponseEntity.noContent().build();
    }
}
