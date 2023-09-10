package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.DoadorDTO;
import collectiva.org.collecta.service.DoadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/doadores")
public class DoadorController {

    @Autowired
    private DoadorService doadorService;

    @GetMapping
    public ResponseEntity<List<DoadorDTO>> buscarDoadores() {
        return doadorService.buscarTodosDoadores();
    }
    @GetMapping("/{id}")
    public ResponseEntity<DoadorDTO> buscarDoadorPorId(@PathVariable UUID id) {
        return doadorService.buscarDoadorPorId(id);
    }

    @PostMapping
    public ResponseEntity<DoadorDTO> criarDoador(@RequestBody @Valid DoadorDTO doadorDTO) {
        return doadorService.salvarDoador(doadorDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DoadorDTO> atualizarDoador(@PathVariable UUID id, @RequestBody @Valid DoadorDTO doadorDTO) {
        return doadorService.atualizarDoador(id, doadorDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDoador(@PathVariable UUID id){
        return  doadorService.deletarDoador(id);
    }
}
