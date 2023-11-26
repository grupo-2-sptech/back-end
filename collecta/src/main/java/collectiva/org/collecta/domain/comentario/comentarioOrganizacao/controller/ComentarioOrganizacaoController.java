package collectiva.org.collecta.domain.comentario.comentarioOrganizacao.controller;

import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.ComentarioOrganizacao;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.dto.AssociationComentarioOrganizacaoDTO;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.dto.CreateComentarioOrganizacaoDTO;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.dto.ResponseComentarioOrganizacaoDTO;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.mapper.ComentarioOrganizacaoMapper;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.service.ComentarioOrganizacaoService;
import collectiva.org.collecta.domain.conta.organizacao.Organizacao;
import collectiva.org.collecta.domain.conta.organizacao.service.OrganizacaoService;
import collectiva.org.collecta.domain.postCampanha.Post;
import collectiva.org.collecta.domain.postCampanha.service.PostService;
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
    private final ComentarioOrganizacaoService comentarioService;
    private final OrganizacaoService organizacaoService;
    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<ResponseComentarioOrganizacaoDTO>> buscarComentarios() {
        List<ResponseComentarioOrganizacaoDTO> listaDTO = comentarioService.buscarTodosComentarios().stream()
                .map(ComentarioOrganizacaoMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseComentarioOrganizacaoDTO> buscarComentarioPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(ComentarioOrganizacaoMapper.paraDTO(comentarioService.buscarComentarioPorId(id)));
    }

    @PostMapping
    public ResponseEntity<AssociationComentarioOrganizacaoDTO> criarComentario(@RequestBody @Valid CreateComentarioOrganizacaoDTO comentarioOrganizacaoDTO) {
        Organizacao organizacao = organizacaoService.buscarOrganizacaoPorId(comentarioOrganizacaoDTO.getIdOrganizacao());
        Post post = postService.buscarPostPorId(comentarioOrganizacaoDTO.getIdPost());
        ComentarioOrganizacao comentarioOrganizacao = comentarioService.salvarComentario(ComentarioOrganizacaoMapper.paraEntidade(comentarioOrganizacaoDTO), organizacao, post);
        return ResponseEntity.status(201).body(ComentarioOrganizacaoMapper.paraAssociacaoDTO(comentarioOrganizacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable UUID id) {
        comentarioService.deletarComentario(id);
        return ResponseEntity.noContent().build();
    }
}
