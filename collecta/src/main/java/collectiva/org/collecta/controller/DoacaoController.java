package collectiva.org.collecta.controller;

import collectiva.org.collecta.domain.Doacao;
import collectiva.org.collecta.service.DoacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/doacao")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    @GetMapping
    public ResponseEntity<List<Doacao>> buscarDoacoes() {
        return doacaoService.buscarTodasDoacoes();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Doacao> buscarDoacaoPorId(@PathVariable UUID id) {
        return doacaoService.buscarDoacaoPorId(id);
    }

    @PostMapping
    public ResponseEntity<Doacao> criarDoacao(@RequestBody Doacao doacao) {
        return doacaoService.salvarDoacao(doacao);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Doacao> atualizarDoacao(@PathVariable UUID id, @RequestBody Doacao doacao) {
        return doacaoService.atualizarDoacao(id, doacao);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDoacao(@PathVariable UUID id){
        return  doacaoService.deletarDoacao(id);
    }
}
