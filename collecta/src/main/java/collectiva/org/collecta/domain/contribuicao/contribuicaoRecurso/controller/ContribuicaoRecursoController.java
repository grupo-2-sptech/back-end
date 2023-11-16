package collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.controller;

import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.ContribuicaoRecurso;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.dto.ContribuicaoRecursoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.mapper.ContribuicaoRecursoMapper;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.service.ContribuicaoRecursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contribuicoes/recursos")
@RequiredArgsConstructor
public class ContribuicaoRecursoController {
    private final ContribuicaoRecursoService contribuicaoRecursoService;

    @GetMapping
    public ResponseEntity<List<ContribuicaoRecursoDTO>> buscarContribuicoesRecursos() {
        List<ContribuicaoRecursoDTO> listaDTO = contribuicaoRecursoService.buscarTodasContribuicoesRecursos().stream()
                .map(ContribuicaoRecursoMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContribuicaoRecursoDTO> buscarContribuicaoRecursoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(ContribuicaoRecursoMapper.paraDTO(contribuicaoRecursoService.buscarContribuicaoRecursoPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ContribuicaoRecursoDTO> criarContribuicaoRecurso(@RequestBody @Valid ContribuicaoRecursoDTO contribuicaoRecursoDTO) {
        ContribuicaoRecurso contribuicaoRecurso = contribuicaoRecursoService.salvarContribuicaoRecurso(ContribuicaoRecursoMapper.paraEntidade(contribuicaoRecursoDTO));
        return ResponseEntity.status(201).body(ContribuicaoRecursoMapper.paraDTO(contribuicaoRecurso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContribuicaoRecursoDTO> atualizarContribuicaoRecurso(@PathVariable UUID id, @Valid @RequestBody ContribuicaoRecursoDTO contribuicaoRecursoDTO) {
        ContribuicaoRecurso contribuicaoRecurso = contribuicaoRecursoService.atualizarContribuicaoRecurso(id, ContribuicaoRecursoMapper.paraEntidade(contribuicaoRecursoDTO));
        return ResponseEntity.ok(ContribuicaoRecursoMapper.paraDTO(contribuicaoRecurso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContribuicaoRecurso(@PathVariable UUID id) {
        contribuicaoRecursoService.deletarContribuicaoRecurso(id);
        return ResponseEntity.noContent().build();
    }
}
