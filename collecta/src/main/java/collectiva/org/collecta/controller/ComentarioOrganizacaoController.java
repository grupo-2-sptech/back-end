package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.ComentarioOrganizacaoDTO;
import collectiva.org.collecta.service.ComentarioOrganizacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comentarios/organizacoes")
public class ComentarioOrganizacaoController {

    @Autowired
    private ComentarioOrganizacaoService comentarioService;

    @GetMapping
    public ResponseEntity<List<ComentarioOrganizacaoDTO>> buscarComentarios() {
        return comentarioService.buscarTodosComentarios();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ComentarioOrganizacaoDTO> buscarComentarioPorId(@PathVariable UUID id) {
        return comentarioService.buscarComentarioPorId(id);
    }

    @PostMapping
    public ResponseEntity<ComentarioOrganizacaoDTO> criarComentario(@RequestBody @Valid ComentarioOrganizacaoDTO ComentarioOrganizacaoDTO) {
        return comentarioService.salvarComentario(ComentarioOrganizacaoDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ComentarioOrganizacaoDTO> atualizarComentario(@PathVariable UUID id, @RequestBody @Valid ComentarioOrganizacaoDTO comentarioOrganizacaoDTO) {
        return comentarioService.atualizarComentario(id, comentarioOrganizacaoDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable UUID id){
        return  comentarioService.deletarComentario(id);
    }
}
