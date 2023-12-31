package collectiva.org.collecta.domain.contribuicao.contribuicaoServico.controller;

import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.conta.doador.service.DoadorService;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.ContribuicaoServico;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.dto.AssociationContribuicaoServicoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.dto.CreateContribuicaoServicoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.dto.ResponseContribuicaoServicoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.mapper.ContribuicaoServicoMapper;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.service.ContribuicaoServicoService;
import collectiva.org.collecta.domain.eventoCampanha.EventoCampanha;
import collectiva.org.collecta.domain.eventoCampanha.service.EventoCampanhaService;
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
    private final ContribuicaoServicoService contribuicaoServicoService;
    private final DoadorService doadorService;
    private final EventoCampanhaService eventoCampanhaService;

    @GetMapping
    public ResponseEntity<List<ResponseContribuicaoServicoDTO>> buscarContribuicoesServicos() {
        List<ResponseContribuicaoServicoDTO> listaDTO = contribuicaoServicoService.buscarTodasContribuicoesServicos().stream()
                .map(ContribuicaoServicoMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseContribuicaoServicoDTO> buscarContribuicaoServicoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(ContribuicaoServicoMapper.paraDTO(contribuicaoServicoService.buscarContribuicaoServicoPorId(id)));
    }

    @PostMapping
    public ResponseEntity<AssociationContribuicaoServicoDTO> criarContribuicaoServico(@RequestBody @Valid CreateContribuicaoServicoDTO contribuicaoServicoDTO) {
        Doador doador = doadorService.buscarDoadorPorId(contribuicaoServicoDTO.getIdDoador());
        EventoCampanha eventoCampanha = eventoCampanhaService.buscarEventoCampanhaPorId(contribuicaoServicoDTO.getIdEvento());
        ContribuicaoServico contribuicaoServico = contribuicaoServicoService.salvarContribuicaoServico
                (ContribuicaoServicoMapper.paraEntidade(contribuicaoServicoDTO), doador, eventoCampanha);
        return ResponseEntity.status(201).body(ContribuicaoServicoMapper.paraAssociacaoDTO(contribuicaoServico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationContribuicaoServicoDTO> atualizarStatusCampanha(@PathVariable UUID id, @RequestParam StatusContribuicao statusContribuicao) {
        AssociationContribuicaoServicoDTO responseDTO = ContribuicaoServicoMapper.paraAssociacaoDTO(contribuicaoServicoService.atualizarStatusContribuicao(id, statusContribuicao));
        return ResponseEntity.ok(responseDTO);
    }

}
