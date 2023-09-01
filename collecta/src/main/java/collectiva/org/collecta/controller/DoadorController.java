package collectiva.org.collecta.controller;

import collectiva.org.collecta.domain.Doador;
import collectiva.org.collecta.service.DoadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/doador")
public class DoadorController {

    @Autowired
    private DoadorService doadorService;

    @GetMapping
    public ResponseEntity<List<Doador>> buscarDoadores() {
        return doadorService.buscarTodosDoadores();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Doador> buscarDoadorPorId(@PathVariable UUID id) {
        return doadorService.buscarDoadorPorId(id);
    }

    @PostMapping
    public ResponseEntity<Doador> criarDoador(@RequestBody Doador doador) {
        return doadorService.salvarDoador(doador);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Doador> atualizarDoador(@PathVariable UUID id, @RequestBody Doador doador) {
        return doadorService.atualizarDoador(id, doador);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDoador(@PathVariable UUID id){
        return  doadorService.deletarDoador(id);
    }
}
