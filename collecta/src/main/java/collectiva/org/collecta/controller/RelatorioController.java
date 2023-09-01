package collectiva.org.collecta.controller;

import collectiva.org.collecta.domain.Relatorio;
import collectiva.org.collecta.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping
    public ResponseEntity<List<Relatorio>> buscarRelatorios() {
        return relatorioService.buscarTodosRelatorios();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Relatorio> buscarRelatorioPorId(@PathVariable UUID id) {
        return relatorioService.buscarRelatorioPorId(id);
    }

    @PostMapping
    public ResponseEntity<Relatorio> criarRelatorio(@RequestBody Relatorio relatorio) {
        return relatorioService.salvarRelatorio(relatorio);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Relatorio> atualizarRelatorio(@PathVariable UUID id, @RequestBody Relatorio relatorio) {
        return relatorioService.atualizarRelatorio(id, relatorio);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRelatorio(@PathVariable UUID id){
        return  relatorioService.deletarRelatorio(id);
    }
}
