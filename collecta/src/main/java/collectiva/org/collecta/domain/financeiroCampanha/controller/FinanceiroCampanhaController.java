package collectiva.org.collecta.domain.financeiroCampanha.controller;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.campanha.service.CampanhaService;
import collectiva.org.collecta.domain.eventoCampanha.EventoCampanha;
import collectiva.org.collecta.domain.financeiroCampanha.FinanceiroCampanha;
import collectiva.org.collecta.domain.financeiroCampanha.dto.CreateFinanceiroCampanhaDTO;
import collectiva.org.collecta.domain.financeiroCampanha.dto.ResponseFinanceiroCampanhaDTO;
import collectiva.org.collecta.domain.financeiroCampanha.dto.UpdateFinanceiroCampanhaDTO;
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
    private final CampanhaService campanhaService;

    @GetMapping
    public ResponseEntity<List<ResponseFinanceiroCampanhaDTO>> buscarFinanceirosCampanha() {
        List<ResponseFinanceiroCampanhaDTO> listaDTO = financeiroCampanhaService.buscarTodosFinanceirosCampanha().stream()
                .map(FinanceiroCampanhaMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseFinanceiroCampanhaDTO> buscarFinanceiroCampanhaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(FinanceiroCampanhaMapper.paraDTO(financeiroCampanhaService.buscarFinanceiroCampanhaPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ResponseFinanceiroCampanhaDTO> criarFinanceiroCampanha(@RequestBody @Valid CreateFinanceiroCampanhaDTO financeiroCampanhaDTO) {
        Campanha campanha = campanhaService.buscarCampanhaPorId(financeiroCampanhaDTO.getIdCampanha());
        FinanceiroCampanha financeiroCampanha = financeiroCampanhaService.salvarFinanceiroCampanha(FinanceiroCampanhaMapper.paraEntidade(financeiroCampanhaDTO), campanha);
        return ResponseEntity.status(201).body(FinanceiroCampanhaMapper.paraDTO(financeiroCampanha));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseFinanceiroCampanhaDTO> atualizarFinanceiroCampanha(@PathVariable UUID id, @RequestBody @Valid UpdateFinanceiroCampanhaDTO financeiroCampanhaDTO) {
        FinanceiroCampanha financeiroCampanha = financeiroCampanhaService.atualizarFinanceiroCampanha(id, FinanceiroCampanhaMapper.paraEntidadeUpdate(financeiroCampanhaDTO));
        return ResponseEntity.ok(FinanceiroCampanhaMapper.paraDTO(financeiroCampanha));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFinanceiroCampanha(@PathVariable UUID id) {
        financeiroCampanhaService.deletarFinanceiroCampanha(id);
        return ResponseEntity.noContent().build();
    }
}
