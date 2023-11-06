package collectiva.org.collecta.domain.endereco.controller;

import collectiva.org.collecta.domain.endereco.dto.EnderecoDTO;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import collectiva.org.collecta.domain.endereco.service.EnderecoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {
    private final EnderecoService enderecoService;
    private static final String cepUrl = "https://viacep.com.br/ws/";

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> buscarEnderecos() {
        List<EnderecoDTO> lista = enderecoService.buscarTodosEnderecos();
        return ResponseEntity.status(lista.isEmpty() ? 204 : 200).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> buscarEnderecoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(enderecoService.buscarEnderecoPorId(id));
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> criarEndereco(@RequestBody @Valid EnderecoDTO enderecoDTO) {
        return ResponseEntity.status(201).body(enderecoService.salvarEndereco(enderecoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable UUID id, @RequestBody @Valid EnderecoDTO enderecoDTO) {
        return ResponseEntity.ok(enderecoService.atualizarEndereco(id, enderecoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable UUID id) {
        enderecoService.deletarEndereco(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("cep/{cep}")
    public EnderecoDTO buscaCep(@PathVariable String cep) {
        RestTemplate restTemplate = new RestTemplate();
        EnderecoDTO enderecoDTO = restTemplate.getForObject(cepUrl + cep + "/json/", EnderecoDTO.class);
        if (enderecoDTO.getCep() == null) {
            throw new EntidadeNaoEncontradaException("Cep");
        }
        return restTemplate.getForObject(cepUrl + cep + "/json/", EnderecoDTO.class);
    }
}
