package collectiva.org.collecta.controller;

import collectiva.org.collecta.domain.ComentarioOrganizacao;
import collectiva.org.collecta.service.ComentarioOrganizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/comentarios/organizacao")
public class ComentarioOrganizacaoController {

    @Autowired
    private ComentarioOrganizacaoService comentarioService;

    @GetMapping
    public ResponseEntity<List<ComentarioOrganizacao>> buscarComentarios() {
        return comentarioService.buscarTodosComentarios();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ComentarioOrganizacao>> buscarComentarioPorId(@PathVariable UUID id) {
        return comentarioService.buscarComentarioPorId(id);
    }

    @PostMapping
    public ResponseEntity<ComentarioOrganizacao> criarComentario(@RequestBody ComentarioOrganizacao ComentarioOrganizacao) {
        return comentarioService.salvarComentario(ComentarioOrganizacao);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ComentarioOrganizacao> atualizarComentario(@PathVariable UUID id, @RequestBody ComentarioOrganizacao ComentarioOrganizacao) {
        return comentarioService.atualizarComentario(id, ComentarioOrganizacao);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable UUID id){
        return  comentarioService.deletarComentario(id);
    }
}
