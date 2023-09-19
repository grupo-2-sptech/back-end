package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.EnderecoDTO;
import collectiva.org.collecta.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> buscarEnderecos() {
        return enderecoService.buscarTodosEnderecos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> buscarEnderecoPorId(@PathVariable UUID id) {
        return enderecoService.buscarEnderecoPorId(id);
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> criarEndereco(@RequestBody @Valid EnderecoDTO enderecoDTO) {
        return enderecoService.salvarEndereco(enderecoDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable UUID id, @RequestBody @Valid EnderecoDTO enderecoDTO) {
        return enderecoService.atualizarEndereco(id, enderecoDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable UUID id){
        return  enderecoService.deletarEndereco(id);
    }
}
