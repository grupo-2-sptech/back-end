package collectiva.org.collecta.domain.contribuicao.contribuicaoServico.controller;

import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.ContribuicaoServico;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.dto.ContribuicaoServicoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.mapper.ContribuicaoServicoMapper;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.service.ContribuicaoServicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contribuicoes/servicos")
@RequiredArgsConstructor
public class ContribuicaoServicoController {
    private final ContribuicaoServicoService contribuicaoServicoService;

    @GetMapping
    public ResponseEntity<List<ContribuicaoServicoDTO>> buscarContribuicoesServicos() {
        List<ContribuicaoServicoDTO> listaDTO = contribuicaoServicoService.buscarTodasContribuicoesServicos().stream()
                .map(ContribuicaoServicoMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContribuicaoServicoDTO> buscarContribuicaoServicoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(ContribuicaoServicoMapper.paraDTO(contribuicaoServicoService.buscarContribuicaoServicoPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ContribuicaoServicoDTO> criarContribuicaoServico(@RequestBody @Valid ContribuicaoServicoDTO contribuicaoServicoDTO) {
        ContribuicaoServico contribuicaoServico = contribuicaoServicoService.salvarContribuicaoServico(ContribuicaoServicoMapper.paraEntidade(contribuicaoServicoDTO));
        return ResponseEntity.status(201).body(ContribuicaoServicoMapper.paraDTO(contribuicaoServico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContribuicaoServicoDTO> atualizarContribuicaoServico(@PathVariable UUID id, @Valid @RequestBody ContribuicaoServicoDTO contribuicaoServicoDTO) {
        ContribuicaoServico contribuicaoServico = contribuicaoServicoService.atualizarContribuicaoServico(id, ContribuicaoServicoMapper.paraEntidade(contribuicaoServicoDTO));
        return ResponseEntity.ok(ContribuicaoServicoMapper.paraDTO(contribuicaoServico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContribuicaoServico(@PathVariable UUID id) {
        contribuicaoServicoService.deletarContribuicaoServico(id);
        return ResponseEntity.noContent().build();
    }
}
