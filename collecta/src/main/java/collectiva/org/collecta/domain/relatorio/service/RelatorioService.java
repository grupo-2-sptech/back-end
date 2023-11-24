package collectiva.org.collecta.domain.relatorio.service;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.relatorio.Relatorio;
import collectiva.org.collecta.domain.relatorio.repository.RelatorioRepository;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RelatorioService {
    private final RelatorioRepository relatorioRepository;

    public Relatorio salvarRelatorio(Relatorio relatorio, Campanha campanha) {
        relatorio.setCampanha(campanha);
        return relatorioRepository.save(relatorio);
    }

    public List<Relatorio> buscarTodosRelatorios() {
        return relatorioRepository.findAll();
    }

    public Relatorio buscarRelatorioPorId(UUID id) {
        return relatorioRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Relatorio"));
    }

    public Relatorio atualizarRelatorio(UUID id, Relatorio relatorio) {
        buscarRelatorioPorId(id);
        relatorio.setId(id);
        return relatorioRepository.save(relatorio);
    }

    public void deletarRelatorio(UUID id) {
        if (!relatorioRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Relatorio");
        }
        relatorioRepository.deleteById(id);
    }
}

