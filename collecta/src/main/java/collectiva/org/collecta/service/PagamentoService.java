package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Pagamento;
import collectiva.org.collecta.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PagamentoService {
    @Autowired
    PagamentoRepository pagamentoRepository;

    public ResponseEntity<Pagamento> salvarPagamento(Pagamento pagamento) {
        pagamentoRepository.salvarPagamento(pagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamento);
    }

    public ResponseEntity<List<Pagamento>> buscarTodosPagamentos() {
        List<Pagamento> pagamento = pagamentoRepository.buscarTodosPagamentos();
        return ResponseEntity.ok().body(pagamento);
    }

    public ResponseEntity<Pagamento> buscarPagamentoPorId(UUID id) {
        Pagamento pagamento = pagamentoRepository.buscarPagamentoPorId(id);
        return ResponseEntity.ok().body(pagamento);
    }
    public ResponseEntity<Pagamento> atualizarPagamento(UUID id, Pagamento pagamento){
        Pagamento pagamentoNovo = pagamentoRepository.atualizarPagamento(id,pagamento);
        return ResponseEntity.ok().body(pagamentoNovo);
    }
    public ResponseEntity<Void> deletarPagamento(UUID id){
        pagamentoRepository.excluirPagamento(id);
        return ResponseEntity.ok().build();
    }
}
