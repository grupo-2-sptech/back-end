package collectiva.org.collecta.domain.campanha.controller;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.campanha.dto.CampanhaDTO;
import collectiva.org.collecta.domain.campanha.mapper.CampanhaMapper;
import collectiva.org.collecta.domain.campanha.service.CampanhaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/campanhas")
@RequiredArgsConstructor
public class CampanhaController {
    private final CampanhaService campanhaService;

    @GetMapping
    public ResponseEntity<List<CampanhaDTO>> buscarCampanhas() {
        List<CampanhaDTO> listaDTO = campanhaService.buscarTodasCampanhas().stream().map
                (CampanhaMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampanhaDTO> buscarCampanhaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(CampanhaMapper.paraDTO(campanhaService.buscarCampanhaPorId(id)));
    }

    @PostMapping
    public ResponseEntity<CampanhaDTO> criarCampanha(@RequestBody @Valid CampanhaDTO campanhaDTO) {
        Campanha campanha = campanhaService.salvarCampanha(CampanhaMapper.paraEntidade(campanhaDTO));
        return ResponseEntity.status(201).body(CampanhaMapper.paraDTO(campanha));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampanhaDTO> atualizarCampanha(@PathVariable UUID id, @Valid @RequestBody CampanhaDTO campanhaDTO) {
        Campanha novaCampanha = campanhaService.atualizarCampanha(id, CampanhaMapper.paraEntidade(campanhaDTO));
        return ResponseEntity.ok(CampanhaMapper.paraDTO(novaCampanha));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCampanha(@PathVariable UUID id) {
        campanhaService.deletarCampanha(id);
        return ResponseEntity.noContent().build();
    }
}
