package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Pagamento;
import collectiva.org.collecta.dto.PagamentoDTO;
import collectiva.org.collecta.mapper.PagamentoMapper;
import collectiva.org.collecta.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;

    public ResponseEntity<PagamentoDTO> salvarPagamento(PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = PagamentoMapper.paraEntidade(pagamentoDTO);
        pagamentoRepository.save(pagamento);
        pagamentoDTO = PagamentoMapper.paraDTO(pagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoDTO);
    }

    public ResponseEntity<List<PagamentoDTO>> buscarTodosPagamentos() {
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        if (pagamentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<PagamentoDTO> pagamentosDTO = pagamentos.stream().map(PagamentoMapper::paraDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(pagamentosDTO);
    }

    public ResponseEntity<PagamentoDTO> buscarPagamentoPorId(UUID id) {
        Optional<Pagamento> pagamento = pagamentoRepository.findById(id);
        if (pagamento.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PagamentoDTO pagamentoDTO = PagamentoMapper.paraDTO(pagamento.get());
        return ResponseEntity.ok().body(pagamentoDTO);
    }

    public ResponseEntity<PagamentoDTO> atualizarPagamento(UUID id, PagamentoDTO pagamentoDTO) {
        Optional<Pagamento> pagamentoAntigo = pagamentoRepository.findById(id);
        if (pagamentoAntigo.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        Pagamento pagamentoExistente = pagamentoAntigo.get();
        Pagamento pagamentoAtualizado = Pagamento.builder()
                .id(pagamentoExistente.getId())
                .plano(pagamentoDTO.getPlano())
                .parcelas(pagamentoDTO.getParcelas())
                .dataHora(LocalDateTime.now())
                .formaPagamento(pagamentoDTO.getFormaPagamento())
                .build();

        pagamentoRepository.save(pagamentoAtualizado);
        pagamentoDTO = PagamentoMapper.paraDTO(pagamentoAtualizado);
        return ResponseEntity.ok().body(pagamentoDTO);
    }

    public ResponseEntity<Void> deletarPagamento(UUID id) {
        if (!pagamentoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pagamentoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

