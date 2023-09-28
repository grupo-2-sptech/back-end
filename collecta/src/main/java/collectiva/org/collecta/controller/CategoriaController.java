package collectiva.org.collecta.controller;

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
        return categoriaService.buscarTodasCategorias();
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscarCategoriaPorId(@PathVariable UUID id) {
        return categoriaService.buscarCategoriaPorId(id);
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> criarCategoria(@RequestBody @Valid CategoriaDTO categoria) {
        return categoriaService.salvarCategoria(categoria);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizarCategoria(@PathVariable UUID id, @Valid @RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.atualizarCategoria(id, categoriaDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable UUID id){
        return  categoriaService.deletarCategoria(id);
    }
}
