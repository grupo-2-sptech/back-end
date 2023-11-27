package collectiva.org.collecta.domain.financeiroCampanha.service;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.financeiroCampanha.FinanceiroCampanha;
import collectiva.org.collecta.domain.financeiroCampanha.repository.FinanceiroCampanhaRepository;
import collectiva.org.collecta.enums.MetaStatus;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FinanceiroCampanhaService {
    private final FinanceiroCampanhaRepository finaceiroCampanhaRepository;

    public FinanceiroCampanha salvarFinanceiroCampanha(FinanceiroCampanha finaceiroCampanha, Campanha campanha) {
        finaceiroCampanha.setCampanha(campanha);
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
        FinanceiroCampanha financeiroCampanhaAntiga = buscarFinanceiroCampanhaPorId(id);
        financeiroCampanha.setId(id);
        financeiroCampanha.setMetaStatus(financeiroCampanhaAntiga.getMetaStatus());
        return finaceiroCampanhaRepository.save(financeiroCampanha);
    }

    public void deletarFinanceiroCampanha(UUID id) {
        if (!finaceiroCampanhaRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Financeiro");
        }
        finaceiroCampanhaRepository.deleteById(id);
    }

    public void somarContribuicao(FinanceiroCampanha financeiroCampanha, BigDecimal valor){
        BigDecimal total = financeiroCampanha.getValorAtingido().add(valor);
        MetaStatus metaStatus;
        metaStatus = (total.compareTo(financeiroCampanha.getValorMeta()) > 0) ? MetaStatus.CONCLUIDO : MetaStatus.EM_ANDAMENTO;
        financeiroCampanha.setValorAtingido(total);
        financeiroCampanha.setMetaStatus(metaStatus);
        finaceiroCampanhaRepository.save(financeiroCampanha);
    }
}

