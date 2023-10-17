package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.CampanhaDTO;
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
        List<PagamentoDTO> lista = pagamentoService.buscarTodosPagamentos();
        return ResponseEntity.status(lista.isEmpty()? 204 : 200).body(lista);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> buscarPagamentoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(pagamentoService.buscarPagamentoPorId(id));
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> criarPagamento(@RequestBody @Valid PagamentoDTO pagamentoDTO) {
        return ResponseEntity.status(201).body(pagamentoService.salvarPagamento(pagamentoDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDTO> atualizarPagamento(@PathVariable UUID id, @RequestBody @Valid PagamentoDTO pagamentoDTO) {
        return ResponseEntity.ok(pagamentoService.atualizarPagamento(id, pagamentoDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPagamento(@PathVariable UUID id){
        pagamentoService.deletarPagamento(id);
        return ResponseEntity.noContent().build();
    }
}
