package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.DoacaoDTO;
import collectiva.org.collecta.service.DoacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/doacoes")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    @GetMapping
    public ResponseEntity<List<DoacaoDTO>> buscarDoacoes() {
        return doacaoService.buscarTodasDoacoes();
    }
    @GetMapping("/{id}")
    public ResponseEntity<DoacaoDTO> buscarDoacaoPorId(@PathVariable UUID id) {
        return doacaoService.buscarDoacaoPorId(id);
    }

    @PostMapping
    public ResponseEntity<DoacaoDTO> criarDoacao(@RequestBody @Valid DoacaoDTO doacaoDTO) {
        return doacaoService.salvarDoacao(doacaoDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DoacaoDTO> atualizarDoacao(@PathVariable UUID id, @RequestBody @Valid DoacaoDTO doacaoDTO) {
        return doacaoService.atualizarDoacao(id, doacaoDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDoacao(@PathVariable UUID id){
        return  doacaoService.deletarDoacao(id);
    }
}
