package collectiva.org.collecta.domain.acaoCampanha.service;

import collectiva.org.collecta.domain.acaoCampanha.AcaoCampanha;
import collectiva.org.collecta.domain.acaoCampanha.repository.AcaoCampanhaRepository;
import collectiva.org.collecta.domain.relatorio.Relatorio;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AcaoCampanhaService {
    private final AcaoCampanhaRepository acoesRepository;

    public List<AcaoCampanha> buscarTodosAcoes() {
        return acoesRepository.findAll();
    }

    public AcaoCampanha buscarAcaoCampanhaPorId(UUID id) {
        return acoesRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("AcaoCampanha"));
    }

    public AcaoCampanha criarAcaoCampanha(AcaoCampanha acaoCampanha, Relatorio relatorio) {
        acaoCampanha.setRelatorio(relatorio);
        return acoesRepository.save(acaoCampanha);
    }

    public AcaoCampanha atualizarAcaoCampanha(UUID id, AcaoCampanha acaoCampanha) {
        AcaoCampanha existeAcao = buscarAcaoCampanhaPorId(id);
        acaoCampanha.setId(id);
        acaoCampanha.setRelatorio(existeAcao.getRelatorio());
        return acoesRepository.save(acaoCampanha);
    }

    public void deletarAcaoCampanha(UUID id) {
        buscarAcaoCampanhaPorId(id);
        acoesRepository.deleteById(id);
    }

}

