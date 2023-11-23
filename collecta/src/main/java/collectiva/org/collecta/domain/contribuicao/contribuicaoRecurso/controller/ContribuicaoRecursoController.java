package collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.controller;

import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.conta.doador.service.DoadorService;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.ContribuicaoRecurso;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.dto.CreateContribuicaoRecursoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.dto.ResponseContribuicaoRecursoDTO;
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
    private final DoadorService doadorService;

    @GetMapping
    public ResponseEntity<List<ResponseContribuicaoRecursoDTO>> buscarContribuicoesRecursos() {
        List<ResponseContribuicaoRecursoDTO> listaDTO = contribuicaoRecursoService.buscarTodasContribuicoesRecursos().stream()
                .map(ContribuicaoRecursoMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseContribuicaoRecursoDTO> buscarContribuicaoRecursoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(ContribuicaoRecursoMapper.paraDTO(contribuicaoRecursoService.buscarContribuicaoRecursoPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ResponseContribuicaoRecursoDTO> criarContribuicaoRecurso(@RequestBody @Valid CreateContribuicaoRecursoDTO contribuicaoRecursoDTO) {
        Doador doador = doadorService.buscarDoadorPorId(contribuicaoRecursoDTO.getIdDoador());
        ContribuicaoRecurso contribuicaoRecurso = contribuicaoRecursoService.salvarContribuicaoRecurso(ContribuicaoRecursoMapper.paraEntidade(contribuicaoRecursoDTO), doador);
        return ResponseEntity.status(201).body(ContribuicaoRecursoMapper.paraDTO(contribuicaoRecurso));
    }

}
