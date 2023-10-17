package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.ContribuicaoMonetariaDTO;
import collectiva.org.collecta.service.ContribuicaoMonetariaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contribuicoes/monetarias")
public class ContribuicaoMonetariaController {

    @Autowired
    private ContribuicaoMonetariaService contribuicaoMonetariaService;

    @GetMapping
    public ResponseEntity<List<ContribuicaoMonetariaDTO>> buscarContribuicoesMonetarias() {
        return ResponseEntity.ok(contribuicaoMonetariaService.buscarTodasContribuicoesMonetarias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContribuicaoMonetariaDTO> buscarContribuicaoMonetariaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(contribuicaoMonetariaService.buscarContribuicaoMonetariaPorId(id));
    }

    @PostMapping
    public ResponseEntity<ContribuicaoMonetariaDTO> criarContribuicaoMonetaria(@RequestBody @Valid ContribuicaoMonetariaDTO contribuicaoMonetaria) {
        return ResponseEntity.status(201).body(contribuicaoMonetariaService.salvarContribuicaoMonetaria(contribuicaoMonetaria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContribuicaoMonetariaDTO> atualizarContribuicaoMonetaria(@PathVariable UUID id, @Valid @RequestBody ContribuicaoMonetariaDTO contribuicaoMonetariaDTO) {
        return ResponseEntity.ok(contribuicaoMonetariaService.atualizarContribuicaoMonetaria(id, contribuicaoMonetariaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContribuicaoMonetaria(@PathVariable UUID id) {
        contribuicaoMonetariaService.deletarContribuicaoMonetaria(id);
        return ResponseEntity.noContent().build();
    }
}
