package collectiva.org.collecta.domain.comentario.comentarioDoador.controller;

import collectiva.org.collecta.domain.comentario.comentarioDoador.dto.ComentarioDoadorDTO;
import collectiva.org.collecta.domain.comentario.comentarioDoador.service.ComentarioDoadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comentarios/doadores")
@RequiredArgsConstructor
public class ComentarioDoadorController {
    private final ComentarioDoadorService comentarioService;

    @GetMapping
    public ResponseEntity<List<ComentarioDoadorDTO>> buscarComentarios() {
        List<ComentarioDoadorDTO> lista = comentarioService.buscarTodosComentarios();
        return ResponseEntity.status(lista.isEmpty() ? 204 : 200).body(lista);
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
