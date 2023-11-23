package collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.controller;

import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.conta.doador.service.DoadorService;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.ContribuicaoRecurso;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.dto.CreateContribuicaoRecursoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.dto.ResponseContribuicaoRecursoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.mapper.ContribuicaoRecursoMapper;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.service.ContribuicaoRecursoService;
import collectiva.org.collecta.domain.recurso.Recurso;
import collectiva.org.collecta.domain.recurso.service.RecursoService;
import collectiva.org.collecta.enums.StatusContribuicao;
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
    private final RecursoService recursoService;

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
        Recurso recurso = recursoService.buscarRecursoPorId(contribuicaoRecursoDTO.getIdRecurso());
        ContribuicaoRecurso contribuicaoRecurso = contribuicaoRecursoService.salvarContribuicaoRecurso
                (ContribuicaoRecursoMapper.paraEntidade(contribuicaoRecursoDTO), doador, recurso);
        return ResponseEntity.status(201).body(ContribuicaoRecursoMapper.paraDTO(contribuicaoRecurso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseContribuicaoRecursoDTO> atualizarStatusCampanha(@PathVariable UUID id, @RequestParam StatusContribuicao statusContribuicao) {
        ResponseContribuicaoRecursoDTO responseDTO = ContribuicaoRecursoMapper.paraDTO(contribuicaoRecursoService.atualizarStatusContribuicao(id, statusContribuicao));
        return ResponseEntity.ok(responseDTO);
    }

}
