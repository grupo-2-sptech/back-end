package collectiva.org.collecta.domain.comentario.comentarioDoador.controller;

import collectiva.org.collecta.domain.comentario.comentarioDoador.dto.AssociationComentarioDoadorDTO;
import collectiva.org.collecta.domain.comentario.comentarioDoador.dto.CreateComentarioDoadorDTO;
import collectiva.org.collecta.domain.comentario.comentarioDoador.dto.ResponseComentarioDoadorDTO;
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

    public ResponseEntity<List<ResponseComentarioDoadorDTO>> buscarComentarios() {
        List<ResponseComentarioDoadorDTO> listaDTO = comentarioService.buscarTodosComentarios();
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseComentarioDoadorDTO> buscarComentarioPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(comentarioService.buscarComentarioPorId(id));
    }

    @PostMapping("/{idDoador}/{idPost}")
    public ResponseEntity<AssociationComentarioDoadorDTO> criarComentario(@PathVariable UUID idDoador, @PathVariable UUID idPost, @RequestBody @Valid CreateComentarioDoadorDTO comentarioDoadorDTO) {
        return ResponseEntity.status(201).body(comentarioService.criarComentario(comentarioDoadorDTO, idDoador, idPost));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable UUID id) {
        comentarioService.deletarComentario(id);
        return ResponseEntity.noContent().build();
    }
}
