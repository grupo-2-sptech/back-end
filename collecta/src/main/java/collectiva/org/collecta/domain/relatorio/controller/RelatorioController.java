package collectiva.org.collecta.domain.relatorio.controller;

import collectiva.org.collecta.domain.relatorio.dto.CreateRelatorioDTO;
import collectiva.org.collecta.domain.relatorio.dto.GeneratorRelatorioDTO;
import collectiva.org.collecta.domain.relatorio.dto.ResponseRelatorioDTO;
import collectiva.org.collecta.domain.relatorio.service.RelatorioServiceFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatorioController {
    private final RelatorioServiceFacade relatorioServiceF;

    @GetMapping
    public ResponseEntity<List<ResponseRelatorioDTO>> buscarRelatorios() {
        List<ResponseRelatorioDTO> listaDTO = relatorioServiceF.buscarTodosRelatorios();
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseRelatorioDTO> buscarRelatorioPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(relatorioServiceF.buscarRelatorioPorId(id));
    }

    @GetMapping("/gerar/{id}")
    public ResponseEntity<GeneratorRelatorioDTO> gerarRelatorioPorCampanha(@PathVariable UUID id, @RequestParam LocalDateTime inicio, @RequestParam LocalDateTime fim) {
        return ResponseEntity.ok(relatorioServiceF.gerarRelatorioPorCampanha(id, inicio, fim));
    }

    @PostMapping("/{idCampanha}")
    public ResponseEntity<ResponseRelatorioDTO> criarRelatorio(@PathVariable UUID idCampanha, @RequestBody @Valid CreateRelatorioDTO relatorioDTO) {
        return ResponseEntity.status(201).body(relatorioServiceF.criarRelatorio(idCampanha, relatorioDTO));
    }
}
