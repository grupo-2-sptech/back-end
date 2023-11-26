package collectiva.org.collecta.domain.relatorio.service;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.relatorio.Relatorio;
import collectiva.org.collecta.domain.relatorio.mapper.RelatorioMapper;
import collectiva.org.collecta.domain.relatorio.repository.RelatorioRepository;
import collectiva.org.collecta.enums.StatusContribuicao;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Relatorio gerarRelatorioPorCampanha(UUID idCampanha, LocalDateTime inicio, LocalDateTime fim) {
        return RelatorioMapper.paraGeradorEntidade(relatorioRepository.gerarRelatorioPorCampanha(idCampanha, inicio, fim, StatusContribuicao.FINALIZADA));
    }

    public List<Relatorio> buscarTodosRelatorios() {
        return relatorioRepository.findAll();
    }

    public Relatorio buscarRelatorioPorId(UUID id) {
        return relatorioRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Relatorio"));
    }

}

