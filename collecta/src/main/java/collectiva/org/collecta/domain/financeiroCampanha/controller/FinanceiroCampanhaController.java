package collectiva.org.collecta.domain.financeiroCampanha.controller;

import collectiva.org.collecta.domain.financeiroCampanha.dto.AssociationFinanceiroCampanhaDTO;
import collectiva.org.collecta.domain.financeiroCampanha.dto.CreateFinanceiroCampanhaDTO;
import collectiva.org.collecta.domain.financeiroCampanha.dto.ResponseFinanceiroCampanhaDTO;
import collectiva.org.collecta.domain.financeiroCampanha.dto.UpdateFinanceiroCampanhaDTO;
import collectiva.org.collecta.domain.financeiroCampanha.service.FinanceiroCampanhaServiceFacade;
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
    private final FinanceiroCampanhaServiceFacade financeiroCampanhaServiceF;

    @GetMapping
    public ResponseEntity<List<ResponseFinanceiroCampanhaDTO>> buscarFinanceirosCampanha() {
        List<ResponseFinanceiroCampanhaDTO> listaDTO = financeiroCampanhaServiceF.buscarTodosFinanceiroCampanhas();
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseFinanceiroCampanhaDTO> buscarFinanceiroCampanhaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(financeiroCampanhaServiceF.buscarFinanceiroCampanhaPorId(id));
    }

    @PostMapping("/{idCampanha}")
    public ResponseEntity<AssociationFinanceiroCampanhaDTO> criarFinanceiroCampanha(@PathVariable UUID idCampanha, @RequestBody @Valid CreateFinanceiroCampanhaDTO financeiroCampanhaDTO) {
        return ResponseEntity.status(201).body(financeiroCampanhaServiceF.criarFinanceiroCampanha(idCampanha, financeiroCampanhaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationFinanceiroCampanhaDTO> atualizarFinanceiroCampanha(@PathVariable UUID id, @RequestBody @Valid UpdateFinanceiroCampanhaDTO financeiroCampanhaDTO) {
        return ResponseEntity.ok(financeiroCampanhaServiceF.atualizarFinanceiroCampanha(id, financeiroCampanhaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFinanceiroCampanha(@PathVariable UUID id) {
        financeiroCampanhaServiceF.deletarFinanceiroCampanha(id);
        return ResponseEntity.noContent().build();
    }
}
