package collectiva.org.collecta.domain.comentario.comentarioOrganizacao.controller;

import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.ComentarioOrganizacao;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.dto.ComentarioOrganizacaoDTO;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.mapper.ComentarioOrganizacaoMapper;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.service.ComentarioOrganizacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comentarios/organizacoes")
@RequiredArgsConstructor
public class ComentarioOrganizacaoController {
    private final ComentarioOrganizacaoService comentarioService;

    @GetMapping
    public ResponseEntity<List<ComentarioOrganizacaoDTO>> buscarComentarios() {
        List<ComentarioOrganizacaoDTO> listaDTO = comentarioService.buscarTodosComentarios().stream()
                .map(ComentarioOrganizacaoMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioOrganizacaoDTO> buscarComentarioPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(ComentarioOrganizacaoMapper.paraDTO(comentarioService.buscarComentarioPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ComentarioOrganizacaoDTO> criarComentario(@RequestBody @Valid ComentarioOrganizacaoDTO ComentarioOrganizacaoDTO) {
        ComentarioOrganizacao comentarioOrganizacao = comentarioService.salvarComentario(ComentarioOrganizacaoMapper.paraEntidade(ComentarioOrganizacaoDTO));
        return ResponseEntity.status(201).body(ComentarioOrganizacaoMapper.paraDTO(comentarioOrganizacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioOrganizacaoDTO> atualizarComentario(@PathVariable UUID id, @RequestBody @Valid ComentarioOrganizacaoDTO comentarioOrganizacaoDTO) {
        ComentarioOrganizacao comentarioOrganizacao = comentarioService.atualizarComentario(id, ComentarioOrganizacaoMapper.paraEntidade(comentarioOrganizacaoDTO));
        return ResponseEntity.ok(ComentarioOrganizacaoMapper.paraDTO(comentarioOrganizacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable UUID id) {
        comentarioService.deletarComentario(id);
        return ResponseEntity.noContent().build();
    }
}
