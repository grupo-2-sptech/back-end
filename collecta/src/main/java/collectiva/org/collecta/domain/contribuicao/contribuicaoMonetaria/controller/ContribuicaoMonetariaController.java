package collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.controller;

import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.dto.AssociationContribuicaoMonetariaDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.dto.CreateContribuicaoMonetariaDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.dto.ResponseContribuicaoMonetariaDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.service.ContribuicaoMonetariaServiceFacade;
import collectiva.org.collecta.enums.StatusContribuicao;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contribuicoes/monetarias")
@RequiredArgsConstructor
public class ContribuicaoMonetariaController {
    private final ContribuicaoMonetariaServiceFacade contribuicaoMonetariaServiceF;

    @GetMapping
    public ResponseEntity<List<ResponseContribuicaoMonetariaDTO>> buscarContribuicoesMonetarias() {
        List<ResponseContribuicaoMonetariaDTO> listaDTO = contribuicaoMonetariaServiceF.buscarTodasContribuicoesMonetarias();
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseContribuicaoMonetariaDTO> buscarContribuicaoMonetariaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(contribuicaoMonetariaServiceF.buscarContribuicaoMonetariaPorId(id));
    }

    @PostMapping("/{idDoador}/{idFinanceiro}")
    public ResponseEntity<AssociationContribuicaoMonetariaDTO> salvarContribuicaoMonetaria(@PathVariable UUID idDoador, @PathVariable UUID idFinanceiro, @RequestBody @Valid CreateContribuicaoMonetariaDTO contribuicaoMonetariaDTO) {
        return ResponseEntity.status(201).body(contribuicaoMonetariaServiceF.salvarContribuicaoMonetaria(idDoador, idFinanceiro, contribuicaoMonetariaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationContribuicaoMonetariaDTO> atualizarStatusCampanha(@PathVariable UUID id, @RequestParam StatusContribuicao statusContribuicao) {
        return ResponseEntity.ok(contribuicaoMonetariaServiceF.atualizarStatusContribuicao(id, statusContribuicao));
    }

}
