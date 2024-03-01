package collectiva.org.collecta.domain.contribuicao.contribuicaoServico.controller;

import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.dto.AssociationContribuicaoServicoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.dto.CreateContribuicaoServicoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.dto.ResponseContribuicaoServicoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.service.ContribuicaoServicoServiceFacade;
import collectiva.org.collecta.enums.StatusContribuicao;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contribuicoes/servicos")
@RequiredArgsConstructor
public class ContribuicaoServicoController {
    private final ContribuicaoServicoServiceFacade contribuicaoServicoServiceF;

    @GetMapping
    public ResponseEntity<List<ResponseContribuicaoServicoDTO>> buscarContribuicoesServicos() {
        List<ResponseContribuicaoServicoDTO> listaDTO = contribuicaoServicoServiceF.buscarTodasContribuicoesServicos();
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseContribuicaoServicoDTO> buscarContribuicaoServicoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(contribuicaoServicoServiceF.buscarContribuicaoServicoPorId(id));
    }

    @PostMapping("/{idDoador}/{idEvento}")
    public ResponseEntity<AssociationContribuicaoServicoDTO> criarContribuicaoServico(@PathVariable UUID idDoador, @PathVariable UUID idEvento, @RequestBody @Valid CreateContribuicaoServicoDTO contribuicaoServicoDTO) {
        return ResponseEntity.status(201).body(contribuicaoServicoServiceF.criarContribuicaoServico(idDoador, idEvento, contribuicaoServicoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationContribuicaoServicoDTO> atualizarStatusCampanha(@PathVariable UUID id, @RequestParam StatusContribuicao statusContribuicao) {
        return ResponseEntity.ok(contribuicaoServicoServiceF.atualizarStatusContribuicao(id, statusContribuicao));
    }

}
