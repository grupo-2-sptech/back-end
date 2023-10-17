package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.CampanhaDTO;
import collectiva.org.collecta.service.CampanhaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/campanhas")
public class CampanhaController {

    @Autowired
    private CampanhaService campanhaService;

    @GetMapping
    public ResponseEntity<List<CampanhaDTO>> buscarCampanhas() {
        List<CampanhaDTO> lista = campanhaService.buscarTodasCampanhas();
        return ResponseEntity.status(lista.isEmpty()? 204 : 200).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampanhaDTO> buscarCampanhaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(campanhaService.buscarCampanhaPorId(id));
    }

    @PostMapping
    public ResponseEntity<CampanhaDTO> criarCampanha(@RequestBody @Valid CampanhaDTO campanha) {
        return ResponseEntity.status(201).body(campanhaService.salvarCampanha(campanha));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampanhaDTO> atualizarCampanha(@PathVariable UUID id, @Valid @RequestBody CampanhaDTO campanhaDTO) {
        return ResponseEntity.ok(campanhaService.atualizarCampanha(id, campanhaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCampanha(@PathVariable UUID id) {
        campanhaService.deletarCampanha(id);
        return ResponseEntity.noContent().build();
    }
}
