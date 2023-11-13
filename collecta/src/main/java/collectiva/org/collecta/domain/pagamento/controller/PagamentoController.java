package collectiva.org.collecta.domain.pagamento.controller;

import collectiva.org.collecta.domain.pagamento.Pagamento;
import collectiva.org.collecta.domain.pagamento.dto.PagamentoDTO;
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

    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> buscarPagamentos() {
        List<PagamentoDTO> listaDTO = pagamentoService.buscarTodosPagamentos().stream()
                .map(PagamentoMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> buscarPagamentoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(PagamentoMapper.paraDTO(pagamentoService.buscarPagamentoPorId(id)));
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> criarPagamento(@RequestBody @Valid PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = pagamentoService.salvarPagamento(PagamentoMapper.paraEntidade(pagamentoDTO));
        return ResponseEntity.status(201).body(PagamentoMapper.paraDTO(pagamento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDTO> atualizarPagamento(@PathVariable UUID id, @RequestBody @Valid PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = pagamentoService.atualizarPagamento(id, PagamentoMapper.paraEntidade(pagamentoDTO));
        return ResponseEntity.ok(PagamentoMapper.paraDTO(pagamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPagamento(@PathVariable UUID id) {
        pagamentoService.deletarPagamento(id);
        return ResponseEntity.noContent().build();
    }
}