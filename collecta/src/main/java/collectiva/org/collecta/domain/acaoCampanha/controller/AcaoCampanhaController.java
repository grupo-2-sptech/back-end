package collectiva.org.collecta.domain.acaoCampanha.controller;

import collectiva.org.collecta.domain.acaoCampanha.AcaoCampanha;
import collectiva.org.collecta.domain.acaoCampanha.dto.AssociationAcaoCampanhaDTO;
import collectiva.org.collecta.domain.acaoCampanha.dto.CreateAcaoCampanhaDTO;
import collectiva.org.collecta.domain.acaoCampanha.dto.ResponseAcaoCampanhaDTO;
import collectiva.org.collecta.domain.acaoCampanha.dto.UpdateAcaoCampanhaDTO;
import collectiva.org.collecta.domain.acaoCampanha.mapper.AcaoCampanhaMapper;
import collectiva.org.collecta.domain.acaoCampanha.service.AcaoCampanhaService;
import collectiva.org.collecta.domain.relatorio.Relatorio;
import collectiva.org.collecta.domain.relatorio.service.RelatorioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/acoes")
@RequiredArgsConstructor
public class AcaoCampanhaController {
    private final AcaoCampanhaService acoesService;
    private final RelatorioService relatorioService;

    @GetMapping
    public ResponseEntity<List<ResponseAcaoCampanhaDTO>> buscarAcoes() {
        List<ResponseAcaoCampanhaDTO> listaDTO = acoesService.buscarTodosAcoes().stream()
                .map(AcaoCampanhaMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAcaoCampanhaDTO> buscarAcaoCampanhaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(AcaoCampanhaMapper.paraDTO(acoesService.buscarAcaoCampanhaPorId(id)));
    }

    @PostMapping
    public ResponseEntity<AssociationAcaoCampanhaDTO> criarAcaoCampanha(@RequestBody @Valid CreateAcaoCampanhaDTO acoesDTO) {
        Relatorio relatorio = relatorioService.buscarRelatorioPorId(acoesDTO.getIdRelatorio());
        AcaoCampanha acoes = acoesService.salvarAcaoCampanha(AcaoCampanhaMapper.paraEntidade(acoesDTO), relatorio);
        return ResponseEntity.status(201).body(AcaoCampanhaMapper.paraAssociacaoDTO(acoes));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationAcaoCampanhaDTO> atualizarAcaoCampanha(@PathVariable UUID id, @RequestBody @Valid UpdateAcaoCampanhaDTO acoesDTO) {
        AcaoCampanha acoes = acoesService.atualizarAcaoCampanha(id, AcaoCampanhaMapper.paraEntidadeUpdate(acoesDTO));
        return ResponseEntity.ok(AcaoCampanhaMapper.paraAssociacaoDTO(acoes));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAcaoCampanha(@PathVariable UUID id) {
        acoesService.deletarAcaoCampanha(id);
        return ResponseEntity.noContent().build();
    }
}
