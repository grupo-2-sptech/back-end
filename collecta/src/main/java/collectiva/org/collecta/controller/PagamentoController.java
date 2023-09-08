package collectiva.org.collecta.controller;

import collectiva.org.collecta.domain.Pagamento;
import collectiva.org.collecta.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public ResponseEntity<List<Pagamento>> buscarPagamentos() {
        return pagamentoService.buscarTodosPagamentos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pagamento>> buscarPagamentoPorId(@PathVariable UUID id) {
        return pagamentoService.buscarPagamentoPorId(id);
    }

    @PostMapping
    public ResponseEntity<Pagamento> criarPagamento(@RequestBody Pagamento pagamento) {
        return pagamentoService.salvarPagamento(pagamento);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> atualizarPagamento(@PathVariable UUID id, @RequestBody Pagamento pagamento) {
        return pagamentoService.atualizarPagamento(id, pagamento);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPagamento(@PathVariable UUID id){
        return  pagamentoService.deletarPagamento(id);
    }
}
