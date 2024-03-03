package collectiva.org.collecta.domain.pagamento.controller;

import collectiva.org.collecta.domain.pagamento.dto.AssociationPagamentoDTO;
import collectiva.org.collecta.domain.pagamento.dto.CreatePagamentoDTO;
import collectiva.org.collecta.domain.pagamento.dto.ResponsePagamentoDTO;
import collectiva.org.collecta.domain.pagamento.service.PagamentoServiceFacade;
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
    private final PagamentoServiceFacade pagamentoServiceF;

    @GetMapping
    public ResponseEntity<List<ResponsePagamentoDTO>> buscarPagamentos() {
        List<ResponsePagamentoDTO> listaDTO = pagamentoServiceF.buscarTodosPagamentos();
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePagamentoDTO> buscarPagamentoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(pagamentoServiceF.buscarPagamentoPorId(id));
    }

    @PostMapping("/{idContribuicaoM}")
    public ResponseEntity<AssociationPagamentoDTO> criarPagamento(@PathVariable UUID idContribuicaoM, @RequestBody @Valid CreatePagamentoDTO pagamentoDTO) {
        return ResponseEntity.status(201).body(pagamentoServiceF.criarPagamento(idContribuicaoM, pagamentoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPagamento(@PathVariable UUID id) {
        pagamentoServiceF.deletarPagamento(id);
        return ResponseEntity.noContent().build();
    }
}
