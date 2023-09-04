package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Pagamento;
import collectiva.org.collecta.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;

    public ResponseEntity<Pagamento> salvarPagamento(Pagamento pagamento) {
        pagamentoRepository.save(pagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamento);
    }

    public ResponseEntity<List<Pagamento>> buscarTodosPagamentos() {
        List<Pagamento> pagamento = pagamentoRepository.findAll();
        return ResponseEntity.ok().body(pagamento);
    }

    public ResponseEntity<Optional<Pagamento>> buscarPagamentoPorId(UUID id) {
        Optional<Pagamento> pagamento = pagamentoRepository.findById(id);
        return ResponseEntity.ok().body(pagamento);
    }

    public ResponseEntity<Pagamento> atualizarPagamento(UUID id, Pagamento pagamento) {
        Optional<Pagamento> pagamentoAntigo = pagamentoRepository.findById(id);
        Pagamento pagamentoExistente = pagamentoAntigo.get();

        Pagamento pagamentoAtualizado = Pagamento.builder()
                .id(pagamentoExistente.getId())
                .parcelas(pagamento.getParcelas())
                .formaPagamento(pagamento.getFormaPagamento())
                .build();

        pagamentoRepository.save(pagamentoAtualizado);

        return ResponseEntity.ok().body(pagamentoAtualizado);
    }

    public ResponseEntity<Void> deletarPagamento(UUID id) {
        pagamentoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

