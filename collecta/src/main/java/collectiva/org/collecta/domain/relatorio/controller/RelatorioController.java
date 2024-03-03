package collectiva.org.collecta.domain.relatorio.controller;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.campanha.service.CampanhaService;
import collectiva.org.collecta.domain.relatorio.Relatorio;
import collectiva.org.collecta.domain.relatorio.dto.CreateRelatorioDTO;
import collectiva.org.collecta.domain.relatorio.dto.GeneratorRelatorioDTO;
import collectiva.org.collecta.domain.relatorio.dto.ResponseRelatorioDTO;
import collectiva.org.collecta.domain.relatorio.mapper.RelatorioMapper;
import collectiva.org.collecta.domain.relatorio.service.RelatorioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatorioController {
    private final RelatorioService relatorioService;
    private final CampanhaService campanhaService;

    @GetMapping
    public ResponseEntity<List<ResponseRelatorioDTO>> buscarRelatorios() {
        List<ResponseRelatorioDTO> listaDTO = relatorioService.buscarTodosRelatorios().stream()
                .map(RelatorioMapper::paraDTO).toList();
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/gerar/{id}")
    public ResponseEntity<GeneratorRelatorioDTO> gerarRelatorioPorCampanha(@PathVariable UUID id, @RequestParam LocalDateTime inicio, @RequestParam LocalDateTime fim) {
        campanhaService.buscarCampanhaPorId(id);
        return ResponseEntity.ok(RelatorioMapper.paraDTOGerador(relatorioService.gerarRelatorioPorCampanha(id, inicio, fim)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseRelatorioDTO> buscarRelatorioPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(RelatorioMapper.paraDTO(relatorioService.buscarRelatorioPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ResponseRelatorioDTO> criarRelatorio(@RequestBody @Valid CreateRelatorioDTO relatorioDTO) {
        Campanha campanha = campanhaService.buscarCampanhaPorId(relatorioDTO.getIdCampanha());
        Relatorio relatorio = relatorioService.criarRelatorio(RelatorioMapper.paraEntidade(relatorioDTO), campanha);
        return ResponseEntity.status(201).body(RelatorioMapper.paraDTO(relatorio));
    }
}
