package collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.controller;

import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.conta.doador.service.DoadorService;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.ContribuicaoMonetaria;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.dto.CreateContribuicaoMonetariaDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.dto.ResponseContribuicaoMonetariaDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.mapper.ContribuicaoMonetariaMapper;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.service.ContribuicaoMonetariaService;
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
    private final ContribuicaoMonetariaService contribuicaoMonetariaService;
    private final DoadorService doadorService;

    @GetMapping
    public ResponseEntity<List<ResponseContribuicaoMonetariaDTO>> buscarContribuicoesMonetarias() {
        List<ResponseContribuicaoMonetariaDTO> listaDTO = contribuicaoMonetariaService.buscarTodasContribuicoesMonetarias()
                .stream().map(ContribuicaoMonetariaMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseContribuicaoMonetariaDTO> buscarContribuicaoMonetariaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(ContribuicaoMonetariaMapper.paraDTO(contribuicaoMonetariaService.buscarContribuicaoMonetariaPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ResponseContribuicaoMonetariaDTO> criarContribuicaoMonetaria(@RequestBody @Valid CreateContribuicaoMonetariaDTO contribuicaoMonetariaDTO) {
        Doador doador = doadorService.buscarDoadorPorId(contribuicaoMonetariaDTO.getIdDoador());
        ContribuicaoMonetaria contribuicaoMonetaria = contribuicaoMonetariaService.salvarContribuicaoMonetaria(ContribuicaoMonetariaMapper.paraEntidade(contribuicaoMonetariaDTO), doador);
        return ResponseEntity.status(201).body(ContribuicaoMonetariaMapper.paraDTO(contribuicaoMonetaria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseContribuicaoMonetariaDTO> atualizarStatusCampanha(@PathVariable UUID id, @RequestParam StatusContribuicao statusContribuicao){
        ResponseContribuicaoMonetariaDTO responseDTO = ContribuicaoMonetariaMapper.paraDTO(contribuicaoMonetariaService.atualizarStatusContribuicao(id, statusContribuicao));
        return ResponseEntity.ok(responseDTO);
    }

}
