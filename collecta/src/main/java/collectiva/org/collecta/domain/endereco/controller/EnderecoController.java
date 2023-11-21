package collectiva.org.collecta.domain.endereco.controller;

import collectiva.org.collecta.domain.endereco.Endereco;
import collectiva.org.collecta.domain.endereco.dto.CreateEnderecoDTO;
import collectiva.org.collecta.domain.endereco.dto.ResponseEnderecoDTO;
import collectiva.org.collecta.domain.endereco.mapper.EnderecoMapper;
import collectiva.org.collecta.domain.endereco.service.EnderecoService;
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
    private final EnderecoService enderecoService;
    private static final String cepUrl = "https://viacep.com.br/ws/";

    @GetMapping
    public ResponseEntity<List<ResponseEnderecoDTO>> buscarEnderecos() {
        List<ResponseEnderecoDTO> listaDTO = enderecoService.buscarTodosEnderecos().stream()
                .map(EnderecoMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEnderecoDTO> buscarEnderecoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(EnderecoMapper.paraDTO(enderecoService.buscarEnderecoPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ResponseEnderecoDTO> criarEndereco(@RequestBody @Valid CreateEnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoService.salvarEndereco(EnderecoMapper.paraEntidade(enderecoDTO));
        return ResponseEntity.status(201).body(EnderecoMapper.paraDTO(endereco));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEnderecoDTO> atualizarEndereco(@PathVariable UUID id, @RequestBody @Valid CreateEnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoService.atualizarEndereco(id, EnderecoMapper.paraEntidade(enderecoDTO));
        return ResponseEntity.ok(EnderecoMapper.paraDTO(endereco));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable UUID id) {
        enderecoService.deletarEndereco(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("cep/{cep}")
    public ResponseEnderecoDTO buscaCep(@PathVariable String cep) {
        RestTemplate restTemplate = new RestTemplate();
        CreateEnderecoDTO enderecoDTO = restTemplate.getForObject(cepUrl + cep + "/json/", CreateEnderecoDTO.class);
        if (enderecoDTO.getCep() == null) {
            throw new EntidadeNaoEncontradaException("Cep");
        }
        return restTemplate.getForObject(cepUrl + cep + "/json/", ResponseEnderecoDTO.class);
    }
}
