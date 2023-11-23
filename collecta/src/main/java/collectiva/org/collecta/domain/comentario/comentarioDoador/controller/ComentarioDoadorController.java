package collectiva.org.collecta.domain.comentario.comentarioDoador.controller;

import collectiva.org.collecta.domain.comentario.comentarioDoador.ComentarioDoador;
import collectiva.org.collecta.domain.comentario.comentarioDoador.dto.CreateComentarioDoadorDTO;
import collectiva.org.collecta.domain.comentario.comentarioDoador.dto.ResponseComentarioDoadorDTO;
import collectiva.org.collecta.domain.comentario.comentarioDoador.mapper.ComentarioDoadorMapper;
import collectiva.org.collecta.domain.comentario.comentarioDoador.service.ComentarioDoadorService;
import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.conta.doador.service.DoadorService;
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
    private final DoadorService doadorService;

    @GetMapping

    public ResponseEntity<List<ResponseComentarioDoadorDTO>> buscarComentarios() {
        List<ResponseComentarioDoadorDTO> listaDTO = comentarioService.buscarTodosComentarios().stream().map
                (ComentarioDoadorMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseComentarioDoadorDTO> buscarComentarioPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(ComentarioDoadorMapper.paraDTO(comentarioService.buscarComentarioPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ResponseComentarioDoadorDTO> criarComentario(@RequestBody @Valid CreateComentarioDoadorDTO comentarioDoadorDTO) {
        Doador doador = doadorService.buscarDoadorPorId(comentarioDoadorDTO.getIdDoador());
        ComentarioDoador comentarioDoador = comentarioService.salvarComentario(ComentarioDoadorMapper.paraEntidade(comentarioDoadorDTO), doador);
        return ResponseEntity.status(201).body(ComentarioDoadorMapper.paraDTO(comentarioDoador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseComentarioDoadorDTO> atualizarComentario(@PathVariable UUID id, @RequestBody @Valid CreateComentarioDoadorDTO comentarioDoadorDTO) {
        ComentarioDoador comentarioDoador = comentarioService.atualizarComentario(id, ComentarioDoadorMapper.paraEntidade(comentarioDoadorDTO));
        return ResponseEntity.ok(ComentarioDoadorMapper.paraDTO(comentarioDoador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable UUID id) {
        comentarioService.deletarComentario(id);
        return ResponseEntity.noContent().build();
    }
}
