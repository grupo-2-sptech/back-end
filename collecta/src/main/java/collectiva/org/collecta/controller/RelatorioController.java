package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.RelatorioDTO;
import collectiva.org.collecta.service.RelatorioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping
    public ResponseEntity<List<RelatorioDTO>> buscarRelatorios() {
        return ResponseEntity.ok(relatorioService.buscarTodosRelatorios());
    }
    @GetMapping("/{id}")
    public ResponseEntity<RelatorioDTO> buscarRelatorioPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(relatorioService.buscarRelatorioPorId(id));
    }

    @PostMapping
    public ResponseEntity<RelatorioDTO> criarRelatorio(@RequestBody @Valid RelatorioDTO relatorioDTO) {
        return ResponseEntity.status(201).body(relatorioService.salvarRelatorio(relatorioDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<RelatorioDTO> atualizarRelatorio(@PathVariable UUID id, @RequestBody @Valid RelatorioDTO relatorioDTO) {
        return ResponseEntity.ok(relatorioService.atualizarRelatorio(id, relatorioDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRelatorio(@PathVariable UUID id){
        relatorioService.deletarRelatorio(id);
        return ResponseEntity.noContent().build();
    }
}
