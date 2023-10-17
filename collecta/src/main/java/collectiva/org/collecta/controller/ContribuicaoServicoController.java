package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.ContribuicaoServicoDTO;
import collectiva.org.collecta.service.ContribuicaoServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contribuicoes/servicos")
public class ContribuicaoServicoController {

    @Autowired
    private ContribuicaoServicoService contribuicaoServicoService;

    @GetMapping
    public ResponseEntity<List<ContribuicaoServicoDTO>> buscarContribuicoesServicos() {
        return ResponseEntity.ok(contribuicaoServicoService.buscarTodasContribuicoesServicos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ContribuicaoServicoDTO> buscarContribuicaoServicoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(contribuicaoServicoService.buscarContribuicaoServicoPorId(id));
    }

    @PostMapping
    public ResponseEntity<ContribuicaoServicoDTO> criarContribuicaoServico(@RequestBody @Valid ContribuicaoServicoDTO contribuicaoServico) {
        return ResponseEntity.status(201).body(contribuicaoServicoService.salvarContribuicaoServico(contribuicaoServico));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ContribuicaoServicoDTO> atualizarContribuicaoServico(@PathVariable UUID id, @Valid @RequestBody ContribuicaoServicoDTO contribuicaoServicoDTO) {
        return ResponseEntity.ok(contribuicaoServicoService.atualizarContribuicaoServico(id, contribuicaoServicoDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContribuicaoServico(@PathVariable UUID id){
        contribuicaoServicoService.deletarContribuicaoServico(id);
        return ResponseEntity.noContent().build();
    }
}
