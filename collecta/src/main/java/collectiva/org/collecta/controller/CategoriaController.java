package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.CampanhaDTO;
import collectiva.org.collecta.dto.CategoriaDTO;
import collectiva.org.collecta.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> buscarCategorias() {
        List<CategoriaDTO> lista = categoriaService.buscarTodasCategorias();
        return ResponseEntity.status(lista.isEmpty()? 204 : 200).body(lista);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscarCategoriaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(categoriaService.buscarCategoriaPorId(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> criarCategoria(@RequestBody @Valid CategoriaDTO categoria) {
        return ResponseEntity.status(201).body(categoriaService.salvarCategoria(categoria));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizarCategoria(@PathVariable UUID id, @Valid @RequestBody CategoriaDTO categoriaDTO) {
        return ResponseEntity.ok(categoriaService.atualizarCategoria(id, categoriaDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable UUID id){
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
