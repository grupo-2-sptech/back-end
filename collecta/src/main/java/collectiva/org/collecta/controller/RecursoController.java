package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.RecursoDTO;
import collectiva.org.collecta.service.RecursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/recursos")
public class RecursoController {

    @Autowired
    private RecursoService recursoService;

    @GetMapping
    public ResponseEntity<List<RecursoDTO>> buscarRecursos() {
        return ResponseEntity.ok(recursoService.buscarTodosRecursos());
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
    public ResponseEntity<Void> deletarRecurso(@PathVariable UUID id){
        recursoService.deletarRecurso(id);
        return ResponseEntity.noContent().build();
    }
}
