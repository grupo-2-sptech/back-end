package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.PagamentoDTO;
import collectiva.org.collecta.service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> buscarPagamentos() {
        return pagamentoService.buscarTodosPagamentos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> buscarPagamentoPorId(@PathVariable UUID id) {
        return pagamentoService.buscarPagamentoPorId(id);
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> criarPagamento(@RequestBody @Valid PagamentoDTO pagamentoDTO) {
        return pagamentoService.salvarPagamento(pagamentoDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDTO> atualizarPagamento(@PathVariable UUID id, @RequestBody @Valid PagamentoDTO pagamentoDTO) {
        return pagamentoService.atualizarPagamento(id, pagamentoDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPagamento(@PathVariable UUID id){
        return  pagamentoService.deletarPagamento(id);
    }
}
