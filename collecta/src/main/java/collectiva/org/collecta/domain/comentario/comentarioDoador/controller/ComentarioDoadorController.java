package collectiva.org.collecta.domain.comentario.comentarioDoador.controller;

import collectiva.org.collecta.domain.comentario.comentarioDoador.dto.AssociationComentarioDoadorDTO;
import collectiva.org.collecta.domain.comentario.comentarioDoador.dto.CreateComentarioDoadorDTO;
import collectiva.org.collecta.domain.comentario.comentarioDoador.dto.ResponseComentarioDoadorDTO;
import collectiva.org.collecta.domain.comentario.comentarioDoador.service.ComentarioDoadorServiceFacade;
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
    private final ComentarioDoadorServiceFacade comentarioServiceF;

    @GetMapping

    public ResponseEntity<List<ResponseComentarioDoadorDTO>> buscarComentarios() {
        List<ResponseComentarioDoadorDTO> listaDTO = comentarioServiceF.buscarTodosComentarios();
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseComentarioDoadorDTO> buscarComentarioPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(comentarioServiceF.buscarComentarioPorId(id));
    }

    @PostMapping("/{idDoador}/{idPost}")
    public ResponseEntity<AssociationComentarioDoadorDTO> criarComentario(@PathVariable UUID idDoador, @PathVariable UUID idPost, @RequestBody @Valid CreateComentarioDoadorDTO comentarioDoadorDTO) {
        return ResponseEntity.status(201).body(comentarioServiceF.criarComentario(idDoador, idPost, comentarioDoadorDTO));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable UUID id) {
        comentarioServiceF.deletarComentario(id);
        return ResponseEntity.noContent().build();
    }
}
