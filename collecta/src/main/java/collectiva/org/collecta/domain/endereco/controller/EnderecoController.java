package collectiva.org.collecta.domain.endereco.controller;

import collectiva.org.collecta.domain.endereco.Endereco;
import collectiva.org.collecta.domain.endereco.dto.CepDTO;
import collectiva.org.collecta.domain.endereco.dto.CreateEnderecoDTO;
import collectiva.org.collecta.domain.endereco.dto.ResponseEnderecoDTO;
import collectiva.org.collecta.domain.endereco.dto.UpdateEnderecoDTO;
import collectiva.org.collecta.domain.endereco.mapper.EnderecoMapper;
import collectiva.org.collecta.domain.endereco.service.EnderecoService;
import collectiva.org.collecta.domain.eventoCampanha.EventoCampanha;
import collectiva.org.collecta.domain.eventoCampanha.service.EventoCampanhaService;
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
    private final EventoCampanhaService eventoCampanhaService;
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
        EventoCampanha eventoCampanha = eventoCampanhaService.buscarEventoCampanhaPorId(enderecoDTO.getIdEventoCampanha());
        Endereco endereco = enderecoService.salvarEndereco(EnderecoMapper.paraEntidade(enderecoDTO), eventoCampanha);
        return ResponseEntity.status(201).body(EnderecoMapper.paraDTO(endereco));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEnderecoDTO> atualizarEndereco(@PathVariable UUID id, @RequestBody @Valid UpdateEnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoService.atualizarEndereco(id, EnderecoMapper.paraEntidadeUpdate(enderecoDTO));
        return ResponseEntity.ok(EnderecoMapper.paraDTO(endereco));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable UUID id) {
        enderecoService.deletarEndereco(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("cep/{cep}")
    public CepDTO buscaCep(@PathVariable String cep) {
        RestTemplate restTemplate = new RestTemplate();
        CepDTO cepDTO = restTemplate.getForObject(cepUrl + cep + "/json/", CepDTO.class);
        if (cepDTO.getCep() == null) {
            throw new EntidadeNaoEncontradaException("Cep");
        }
        return restTemplate.getForObject(cepUrl + cep + "/json/", CepDTO.class);
    }
}
