package collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.controller;

import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.ContribuicaoMonetaria;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.dto.ContribuicaoMonetariaDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.mapper.ContribuicaoMonetariaMapper;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.service.ContribuicaoMonetariaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contribuicoes/monetarias")
@RequiredArgsConstructor
public class ContribuicaoMonetariaController {
    private final ContribuicaoMonetariaService contribuicaoMonetariaService;

    @GetMapping
    public ResponseEntity<List<ContribuicaoMonetariaDTO>> buscarContribuicoesMonetarias() {
        List<ContribuicaoMonetariaDTO> listaDTO = contribuicaoMonetariaService.buscarTodasContribuicoesMonetarias()
                .stream().map(ContribuicaoMonetariaMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContribuicaoMonetariaDTO> buscarContribuicaoMonetariaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(ContribuicaoMonetariaMapper.paraDTO(contribuicaoMonetariaService.buscarContribuicaoMonetariaPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ContribuicaoMonetariaDTO> criarContribuicaoMonetaria(@RequestBody @Valid ContribuicaoMonetariaDTO contribuicaoMonetariaDTO) {
        ContribuicaoMonetaria contribuicaoMonetaria = contribuicaoMonetariaService.salvarContribuicaoMonetaria(ContribuicaoMonetariaMapper.paraEntidade(contribuicaoMonetariaDTO));
        return ResponseEntity.status(201).body(ContribuicaoMonetariaMapper.paraDTO(contribuicaoMonetaria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContribuicaoMonetariaDTO> atualizarContribuicaoMonetaria(@PathVariable UUID id, @Valid @RequestBody ContribuicaoMonetariaDTO contribuicaoMonetariaDTO) {
        ContribuicaoMonetaria contribuicaoMonetaria = contribuicaoMonetariaService.atualizarContribuicaoMonetaria(id, ContribuicaoMonetariaMapper.paraEntidade(contribuicaoMonetariaDTO));
        return ResponseEntity.ok(ContribuicaoMonetariaMapper.paraDTO(contribuicaoMonetaria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContribuicaoMonetaria(@PathVariable UUID id) {
        contribuicaoMonetariaService.deletarContribuicaoMonetaria(id);
        return ResponseEntity.noContent().build();
    }
}
