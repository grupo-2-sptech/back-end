package collectiva.org.collecta.domain.financeiroCampanha.service;

import collectiva.org.collecta.domain.financeiroCampanha.FinanceiroCampanha;
import collectiva.org.collecta.domain.financeiroCampanha.repository.FinanceiroCampanhaRepository;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FinanceiroCampanhaService {
    private final FinanceiroCampanhaRepository finaceiroCampanhaRepository;

    public FinanceiroCampanha salvarFinanceiroCampanha(FinanceiroCampanha finaceiroCampanha) {
        return finaceiroCampanhaRepository.save(finaceiroCampanha);
    }

    public List<FinanceiroCampanha> buscarTodosFinanceirosCampanha() {
        return finaceiroCampanhaRepository.findAll();
    }

    public FinanceiroCampanha buscarFinanceiroCampanhaPorId(UUID id) {
        return finaceiroCampanhaRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Financeiro"));
    }

    public FinanceiroCampanha atualizarFinanceiroCampanha(UUID id, FinanceiroCampanha financeiroCampanha) {
        buscarFinanceiroCampanhaPorId(id);
        financeiroCampanha.setId(id);
        return finaceiroCampanhaRepository.save(financeiroCampanha);
    }

    public void deletarFinanceiroCampanha(UUID id) {
        if (!finaceiroCampanhaRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Financeiro");
        }
        finaceiroCampanhaRepository.deleteById(id);
    }
}

