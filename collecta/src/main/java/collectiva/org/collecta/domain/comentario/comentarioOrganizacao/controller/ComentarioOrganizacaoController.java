package collectiva.org.collecta.domain.comentario.comentarioOrganizacao.controller;

import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.dto.AssociationComentarioOrganizacaoDTO;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.dto.CreateComentarioOrganizacaoDTO;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.dto.ResponseComentarioOrganizacaoDTO;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.service.ComentarioOrganizacaoServiceFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comentarios/organizacoes")
@RequiredArgsConstructor
public class ComentarioOrganizacaoController {
    private final ComentarioOrganizacaoServiceFacade comentarioServiceF;

    @GetMapping
    public ResponseEntity<List<ResponseComentarioOrganizacaoDTO>> buscarComentarios() {
        List<ResponseComentarioOrganizacaoDTO> listaDTO = comentarioServiceF.buscarTodosComentarios();
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseComentarioOrganizacaoDTO> buscarComentarioPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(comentarioServiceF.buscarComentarioPorId(id));
    }

    @PostMapping("/{idOrganizacao}/{idPost}")
    public ResponseEntity<AssociationComentarioOrganizacaoDTO> criarComentario(@PathVariable UUID idOrganizacao, @PathVariable UUID idPost, @RequestBody @Valid CreateComentarioOrganizacaoDTO comentarioOrganizacaoDTO) {
        return ResponseEntity.status(201).body(comentarioServiceF.criarComentario(idOrganizacao, idPost, comentarioOrganizacaoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable UUID id) {
        comentarioServiceF.deletarComentario(id);
        return ResponseEntity.noContent().build();
    }
}
