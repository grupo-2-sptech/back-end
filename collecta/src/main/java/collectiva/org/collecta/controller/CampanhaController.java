package collectiva.org.collecta.controller;

import collectiva.org.collecta.domain.Campanha;
import collectiva.org.collecta.service.CampanhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/campanha")
public class CampanhaController {

    @Autowired
    private CampanhaService campanhaService;

    @GetMapping
    public ResponseEntity<List<Campanha>> buscarComentarios() {
        return campanhaService.buscarTodasCampanhas();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Campanha> buscarComentarioPorId(@PathVariable UUID id) {
        return campanhaService.buscarCampanhaPorId(id);
    }

    @PostMapping
    public ResponseEntity<Campanha> criarComentario(@RequestBody Campanha campanha) {
        return campanhaService.salvarCampanha(campanha);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Campanha> atualizarComentario(@PathVariable UUID id, @RequestBody Campanha campanha) {
        return campanhaService.atualizadoCampanha(id, campanha);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable UUID id){
        return  campanhaService.deletarCampanha(id);
    }
}
