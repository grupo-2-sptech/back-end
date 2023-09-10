package collectiva.org.collecta.controller;

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
        return comentarioService.buscarTodosComentarios();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDoadorDTO> buscarComentarioPorId(@PathVariable UUID id) {
        return comentarioService.buscarComentarioPorId(id);
    }

    @PostMapping
    public ResponseEntity<ComentarioDoadorDTO> criarComentario(@RequestBody @Valid ComentarioDoadorDTO comentarioDoador) {
        return comentarioService.salvarComentario(comentarioDoador);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ComentarioDoadorDTO> atualizarComentario(@PathVariable  UUID id, @RequestBody @Valid ComentarioDoadorDTO comentarioDoador) {
        return comentarioService.atualizarComentario(id, comentarioDoador);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable UUID id){
        return  comentarioService.deletarComentario(id);
    }
}
