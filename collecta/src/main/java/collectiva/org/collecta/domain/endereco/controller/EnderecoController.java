package collectiva.org.collecta.domain.endereco.controller;

import collectiva.org.collecta.domain.endereco.dto.*;
import collectiva.org.collecta.domain.endereco.service.EnderecoServiceFacade;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
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
    private final EnderecoServiceFacade enderecoServiceF;
    private static final String cepUrl = "https://viacep.com.br/ws/";

    @GetMapping
    public ResponseEntity<List<ResponseEnderecoDTO>> buscarEnderecos() {
        List<ResponseEnderecoDTO> listaDTO = enderecoServiceF.buscarTodosEnderecos();
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEnderecoDTO> buscarEnderecoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(enderecoServiceF.buscarEnderecoPorId(id));
    }

    @PostMapping("/{idEventoCampanha}")
    public ResponseEntity<AssociationEnderecoDTO> criarEndereco(@PathVariable UUID idEventoCampanha, @RequestBody @Valid CreateEnderecoDTO enderecoDTO) {
        return ResponseEntity.status(201).body(enderecoServiceF.criarEndereco(idEventoCampanha, enderecoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationEnderecoDTO> atualizarEndereco(@PathVariable UUID id, @RequestBody @Valid UpdateEnderecoDTO enderecoDTO) {
        return ResponseEntity.ok(enderecoServiceF.atualizarEndereco(id, enderecoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable UUID id) {
        enderecoServiceF.deletarEndereco(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cep/{cep}")
    public CepDTO buscaCep(@PathVariable String cep) {
        RestTemplate restTemplate = new RestTemplate();
        CepDTO cepDTO = restTemplate.getForObject(cepUrl + cep + "/json/", CepDTO.class);
        assert cepDTO != null;
        if (cepDTO.getCep() == null) {
            throw new EntidadeNaoEncontradaException("Cep");
        }
        return restTemplate.getForObject(cepUrl + cep + "/json/", CepDTO.class);
    }
}
