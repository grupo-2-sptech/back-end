package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Pagamento;
import collectiva.org.collecta.dto.PagamentoDTO;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import collectiva.org.collecta.mapper.PagamentoMapper;
import collectiva.org.collecta.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;

    public PagamentoDTO salvarPagamento(PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = PagamentoMapper.paraEntidade(pagamentoDTO);
        pagamentoRepository.save(pagamento);
        return PagamentoMapper.paraDTO(pagamento);
    }

    public List<PagamentoDTO> buscarTodosPagamentos() {
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        return pagamentos.stream().map(PagamentoMapper::paraDTO).collect(Collectors.toList());
    }

    public PagamentoDTO buscarPagamentoPorId(UUID id) {
        return PagamentoMapper.paraDTO(pagamentoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Pagamento")));
    }

    public PagamentoDTO atualizarPagamento(UUID id, PagamentoDTO pagamentoDTO) {
        buscarPagamentoPorId(id);
        Pagamento pagamentoNovo = PagamentoMapper.paraEntidade(pagamentoDTO);
        pagamentoNovo.setId(id);
        pagamentoRepository.save(pagamentoNovo);
        return PagamentoMapper.paraDTO(pagamentoNovo);
    }

    public void deletarPagamento(UUID id) {
        if (!pagamentoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Pagamento");
        }
        pagamentoRepository.deleteById(id);
    }
}

