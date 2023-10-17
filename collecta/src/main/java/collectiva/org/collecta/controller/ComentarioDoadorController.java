package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.CampanhaDTO;
import collectiva.org.collecta.dto.ComentarioDoadorDTO;
import collectiva.org.collecta.service.ComentarioDoadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comentarios/doadores")
public class ComentarioDoadorController {

    @Autowired
    private ComentarioDoadorService comentarioService;

    @GetMapping
    public ResponseEntity<List<ComentarioDoadorDTO>> buscarComentarios() {
        List<ComentarioDoadorDTO> lista = comentarioService.buscarTodosComentarios();
        return ResponseEntity.status(lista.isEmpty()? 204 : 200).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDoadorDTO> buscarComentarioPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(comentarioService.buscarComentarioPorId(id));
    }

    @PostMapping
    public ResponseEntity<ComentarioDoadorDTO> criarComentario(@RequestBody @Valid ComentarioDoadorDTO comentarioDoador) {
        return ResponseEntity.status(201).body(comentarioService.salvarComentario(comentarioDoador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioDoadorDTO> atualizarComentario(@PathVariable UUID id, @RequestBody @Valid ComentarioDoadorDTO comentarioDoador) {
        return ResponseEntity.ok(comentarioService.atualizarComentario(id, comentarioDoador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable UUID id) {
        comentarioService.deletarComentario(id);
        return ResponseEntity.noContent().build();
    }
}
