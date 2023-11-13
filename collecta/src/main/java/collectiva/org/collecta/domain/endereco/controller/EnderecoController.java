package collectiva.org.collecta.domain.endereco.controller;

import collectiva.org.collecta.domain.endereco.Endereco;
import collectiva.org.collecta.domain.endereco.dto.EnderecoDTO;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {
    private final EnderecoService enderecoService;
    private static final String cepUrl = "https://viacep.com.br/ws/";

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> buscarEnderecos() {
        List<EnderecoDTO> listaDTO = enderecoService.buscarTodosEnderecos().stream()
                .map(EnderecoMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> buscarEnderecoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(EnderecoMapper.paraDTO(enderecoService.buscarEnderecoPorId(id)));
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> criarEndereco(@RequestBody @Valid EnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoService.salvarEndereco(EnderecoMapper.paraEntidade(enderecoDTO));
        return ResponseEntity.status(201).body(EnderecoMapper.paraDTO(endereco));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable UUID id, @RequestBody @Valid EnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoService.atualizarEndereco(id, EnderecoMapper.paraEntidade(enderecoDTO));
        return ResponseEntity.ok(EnderecoMapper.paraDTO(endereco));
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
