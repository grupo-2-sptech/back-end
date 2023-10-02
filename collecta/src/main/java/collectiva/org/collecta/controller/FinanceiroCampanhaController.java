package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.FinanceiroCampanhaDTO;
import collectiva.org.collecta.service.FinanceiroCampanhaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/financeiros")
public class FinanceiroCampanhaController {

    @Autowired
    private FinanceiroCampanhaService financeiroCampanhaService;

    @GetMapping
    public ResponseEntity<List<FinanceiroCampanhaDTO>> buscarFinanceirosCampanha() {
        return financeiroCampanhaService.buscarTodosFinanceirosCampanha();
    }
    @GetMapping("/{id}")
    public ResponseEntity<FinanceiroCampanhaDTO> buscarFinanceiroCampanhaPorId(@PathVariable UUID id) {
        return financeiroCampanhaService.buscarFinanceiroCampanhaPorId(id);
    }

    @PostMapping
    public ResponseEntity<FinanceiroCampanhaDTO> criarFinanceiroCampanha(@RequestBody @Valid FinanceiroCampanhaDTO financeiroCampanhaDTO) {
        return financeiroCampanhaService.salvarFinanceiroCampanha(financeiroCampanhaDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<FinanceiroCampanhaDTO> atualizarFinanceiroCampanha(@PathVariable UUID id, @RequestBody @Valid FinanceiroCampanhaDTO financeiroCampanhaDTO) {
        return financeiroCampanhaService.atualizarFinanceiroCampanha(id, financeiroCampanhaDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFinanceiroCampanha(@PathVariable UUID id){
        return  financeiroCampanhaService.deletarFinanceiroCampanha(id);
    }
}
