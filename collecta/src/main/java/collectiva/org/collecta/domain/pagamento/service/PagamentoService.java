package collectiva.org.collecta.domain.pagamento.service;

import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.ContribuicaoMonetaria;
import collectiva.org.collecta.domain.pagamento.Pagamento;
import collectiva.org.collecta.domain.pagamento.repository.PagamentoRepository;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;

    public Pagamento criarPagamento(Pagamento pagamento, ContribuicaoMonetaria contribuicaoMonetaria) {
        pagamento.setContribuicaoMonetaria(contribuicaoMonetaria);
        return pagamentoRepository.save(pagamento);
    }

    public List<Pagamento> buscarTodosPagamentos() {
        return pagamentoRepository.findAll();
    }

    public Pagamento buscarPagamentoPorId(UUID id) {
        return pagamentoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Pagamento"));
    }


    public void deletarPagamento(UUID id) {
        if (!pagamentoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Pagamento");
        }
        pagamentoRepository.deleteById(id);
    }
}

