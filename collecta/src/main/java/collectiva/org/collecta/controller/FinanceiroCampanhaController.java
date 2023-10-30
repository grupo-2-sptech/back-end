package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.FinanceiroCampanhaDTO;
import collectiva.org.collecta.service.FinanceiroCampanhaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/financeiros")
@RequiredArgsConstructor
public class FinanceiroCampanhaController {
    private final FinanceiroCampanhaService financeiroCampanhaService;

    @GetMapping
    public ResponseEntity<List<FinanceiroCampanhaDTO>> buscarFinanceirosCampanha() {
        List<FinanceiroCampanhaDTO> lista = financeiroCampanhaService.buscarTodosFinanceirosCampanha();
        return ResponseEntity.status(lista.isEmpty() ? 204 : 200).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinanceiroCampanhaDTO> buscarFinanceiroCampanhaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(financeiroCampanhaService.buscarFinanceiroCampanhaPorId(id));
    }

    @PostMapping
    public ResponseEntity<FinanceiroCampanhaDTO> criarFinanceiroCampanha(@RequestBody @Valid FinanceiroCampanhaDTO financeiroCampanhaDTO) {
        return ResponseEntity.status(201).body(financeiroCampanhaService.salvarFinanceiroCampanha(financeiroCampanhaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinanceiroCampanhaDTO> atualizarFinanceiroCampanha(@PathVariable UUID id, @RequestBody @Valid FinanceiroCampanhaDTO financeiroCampanhaDTO) {
        return ResponseEntity.ok(financeiroCampanhaService.atualizarFinanceiroCampanha(id, financeiroCampanhaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFinanceiroCampanha(@PathVariable UUID id) {
        financeiroCampanhaService.deletarFinanceiroCampanha(id);
        return ResponseEntity.noContent().build();
    }
}
