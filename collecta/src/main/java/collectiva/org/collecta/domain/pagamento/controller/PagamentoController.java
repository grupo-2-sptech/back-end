package collectiva.org.collecta.domain.pagamento.controller;

import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.ContribuicaoMonetaria;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.service.ContribuicaoMonetariaService;
import collectiva.org.collecta.domain.pagamento.Pagamento;
import collectiva.org.collecta.domain.pagamento.dto.AssociationPagamentoDTO;
import collectiva.org.collecta.domain.pagamento.dto.CreatePagamentoDTO;
import collectiva.org.collecta.domain.pagamento.dto.ResponsePagamentoDTO;
import collectiva.org.collecta.domain.pagamento.mapper.PagamentoMapper;
import collectiva.org.collecta.domain.pagamento.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {
    private final PagamentoService pagamentoService;
    private final ContribuicaoMonetariaService contribuicaoMonetariaService;

    @GetMapping
    public ResponseEntity<List<ResponsePagamentoDTO>> buscarPagamentos() {
        List<ResponsePagamentoDTO> listaDTO = pagamentoService.buscarTodosPagamentos().stream()
                .map(PagamentoMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePagamentoDTO> buscarPagamentoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(PagamentoMapper.paraDTO(pagamentoService.buscarPagamentoPorId(id)));
    }

    @PostMapping
    public ResponseEntity<AssociationPagamentoDTO> criarPagamento(@RequestBody @Valid CreatePagamentoDTO pagamentoDTO) {
        ContribuicaoMonetaria contribuicaoMonetaria = contribuicaoMonetariaService.buscarContribuicaoMonetariaPorId(pagamentoDTO.getIdContribuicao());
        Pagamento pagamento = pagamentoService.salvarPagamento(PagamentoMapper.paraEntidade(pagamentoDTO), contribuicaoMonetaria);
        return ResponseEntity.status(201).body(PagamentoMapper.paraAssociacaoDTO(pagamento));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPagamento(@PathVariable UUID id) {
        pagamentoService.deletarPagamento(id);
        return ResponseEntity.noContent().build();
    }
}
