package collectiva.org.collecta.domain.financeiroCampanha.controller;

import collectiva.org.collecta.domain.financeiroCampanha.FinanceiroCampanha;
import collectiva.org.collecta.domain.financeiroCampanha.dto.FinanceiroCampanhaDTO;
import collectiva.org.collecta.domain.financeiroCampanha.mapper.FinanceiroCampanhaMapper;
import collectiva.org.collecta.domain.financeiroCampanha.service.FinanceiroCampanhaService;
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
        List<FinanceiroCampanhaDTO> listaDTO = financeiroCampanhaService.buscarTodosFinanceirosCampanha().stream()
                .map(FinanceiroCampanhaMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinanceiroCampanhaDTO> buscarFinanceiroCampanhaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(FinanceiroCampanhaMapper.paraDTO(financeiroCampanhaService.buscarFinanceiroCampanhaPorId(id)));
    }

    @PostMapping
    public ResponseEntity<FinanceiroCampanhaDTO> criarFinanceiroCampanha(@RequestBody @Valid FinanceiroCampanhaDTO financeiroCampanhaDTO) {
        FinanceiroCampanha financeiroCampanha = financeiroCampanhaService.salvarFinanceiroCampanha(FinanceiroCampanhaMapper.paraEntidade(financeiroCampanhaDTO));
        return ResponseEntity.status(201).body(FinanceiroCampanhaMapper.paraDTO(financeiroCampanha));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinanceiroCampanhaDTO> atualizarFinanceiroCampanha(@PathVariable UUID id, @RequestBody @Valid FinanceiroCampanhaDTO financeiroCampanhaDTO) {
        FinanceiroCampanha financeiroCampanha = financeiroCampanhaService.atualizarFinanceiroCampanha(id, FinanceiroCampanhaMapper.paraEntidade(financeiroCampanhaDTO));
        return ResponseEntity.ok(FinanceiroCampanhaMapper.paraDTO(financeiroCampanha));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFinanceiroCampanha(@PathVariable UUID id) {
        financeiroCampanhaService.deletarFinanceiroCampanha(id);
        return ResponseEntity.noContent().build();
    }
}
