package collectiva.org.collecta.domain.comentario.comentarioDoador.controller;

import collectiva.org.collecta.domain.comentario.comentarioDoador.ComentarioDoador;
import collectiva.org.collecta.domain.comentario.comentarioDoador.dto.ComentarioDoadorDTO;
import collectiva.org.collecta.domain.comentario.comentarioDoador.mapper.ComentarioDoadorMapper;
import collectiva.org.collecta.domain.comentario.comentarioDoador.service.ComentarioDoadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comentarios/doadores")
@RequiredArgsConstructor
public class ComentarioDoadorController {
    private final ComentarioDoadorService comentarioService;

    @GetMapping
    public ResponseEntity<List<ComentarioDoadorDTO>> buscarComentarios() {
        List<ComentarioDoadorDTO> listaDTO = comentarioService.buscarTodosComentarios().stream().map
                (ComentarioDoadorMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDoadorDTO> buscarComentarioPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(ComentarioDoadorMapper.paraDTO(comentarioService.buscarComentarioPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ComentarioDoadorDTO> criarComentario(@RequestBody @Valid ComentarioDoadorDTO comentarioDoadorDTO) {
        ComentarioDoador comentarioDoador = comentarioService.salvarComentario(ComentarioDoadorMapper.paraEntidade(comentarioDoadorDTO));
        return ResponseEntity.status(201).body(ComentarioDoadorMapper.paraDTO(comentarioDoador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioDoadorDTO> atualizarComentario(@PathVariable UUID id, @RequestBody @Valid ComentarioDoadorDTO comentarioDoadorDTO) {
        ComentarioDoador comentarioDoador = comentarioService.atualizarComentario(id, ComentarioDoadorMapper.paraEntidade(comentarioDoadorDTO));
        return ResponseEntity.ok(ComentarioDoadorMapper.paraDTO(comentarioDoador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable UUID id) {
        comentarioService.deletarComentario(id);
        return ResponseEntity.noContent().build();
    }
}
