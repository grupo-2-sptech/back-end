package collectiva.org.collecta.controller;

import collectiva.org.collecta.domain.ComentarioDoador;
import collectiva.org.collecta.service.ComentarioDoadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comentarios")
public class ComentarioDoadorController {

    @Autowired
    private ComentarioDoadorService comentarioService;

    @GetMapping
    public ResponseEntity<List<ComentarioDoador>> buscarComentarios() {
        return comentarioService.buscarTodosComentarios();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDoador> buscarComentarioPorId(@PathVariable UUID id) {
        return comentarioService.buscarComentarioPorId(id);
    }

    @PostMapping
    public ResponseEntity<ComentarioDoador> criarComentario(@RequestBody ComentarioDoador comentario) {
        return comentarioService.salvarComentario(comentario);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ComentarioDoador> atualizarComentario(@PathVariable UUID id, @RequestBody ComentarioDoador comentarioDoador) {
        return comentarioService.atualizadoComentario(id, comentarioDoador);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable UUID id){
        return  comentarioService.deletarComentario(id);
    }
}
