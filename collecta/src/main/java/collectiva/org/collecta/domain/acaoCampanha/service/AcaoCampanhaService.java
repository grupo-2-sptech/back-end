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

    public AcaoCampanha salvarAcaoCampanha(AcaoCampanha acoes, Relatorio relatorio) {
        acoes.setRelatorio(relatorio);
        return acoesRepository.save(acoes);
    }

    public List<AcaoCampanha> buscarTodosAcoes() {
        return acoesRepository.findAll();
    }

    public AcaoCampanha buscarAcaoCampanhaPorId(UUID id) {
        return acoesRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Açao"));
    }

    public AcaoCampanha atualizarAcaoCampanha(UUID id, AcaoCampanha acoes) {
        buscarAcaoCampanhaPorId(id);
        acoes.setId(id);
        return acoesRepository.save(acoes);
    }

    public void deletarAcaoCampanha(UUID id) {
        if (!acoesRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("AcaoCampanha");
        }
        acoesRepository.deleteById(id);
    }

}

