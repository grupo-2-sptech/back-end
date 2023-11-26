package collectiva.org.collecta.domain.relatorio.service;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.campanha.repository.CampanhaRepository;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.repository.ContribuicaoMonetariaRepository;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.repository.ContribuicaoRecursoRepository;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.repository.ContribuicaoServicoRepository;
import collectiva.org.collecta.domain.financeiroCampanha.repository.FinanceiroCampanhaRepository;
import collectiva.org.collecta.domain.relatorio.Relatorio;
import collectiva.org.collecta.domain.relatorio.repository.RelatorioRepository;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RelatorioService {
    private final RelatorioRepository relatorioRepository;
    private final CampanhaRepository campanhaRepository;
    private final FinanceiroCampanhaRepository financeiroCampanhaRepository;
    private final ContribuicaoMonetariaRepository contribuicaoMonetariaRepository;
    private final ContribuicaoRecursoRepository contribuicaoRecursoRepository;
    private final ContribuicaoServicoRepository contribuicaoServicoRepository;

    public Relatorio salvarRelatorio(Relatorio relatorio, Campanha campanha) {
        relatorio.setCampanha(campanha);
        return relatorioRepository.save(relatorio);
    }

    public Relatorio gerarRelatorioPorCampanha(UUID idCampanha, LocalDateTime inicio, LocalDateTime fim) {
        BigDecimal valorMeta = Optional.ofNullable(financeiroCampanhaRepository.findValorMetaById(idCampanha))
                .orElse(BigDecimal.ZERO);

        BigDecimal valorAtingido = Optional.ofNullable(financeiroCampanhaRepository.findValorAtingidoById(idCampanha))
                .orElse(BigDecimal.ZERO);

        BigDecimal valorRestante = valorMeta.subtract(valorAtingido).max(BigDecimal.ZERO);
        return Relatorio.builder()
                .valorMeta(valorMeta)
                .valorArrecadado(valorAtingido)
                .valorRestante(valorRestante)
                .visualizacoes(campanhaRepository.findVisualizacoesById(idCampanha))
                .contribuicoesMonetarias(contribuicaoMonetariaRepository.countContribuicaoMonetariaByFinanceiroCampanha_Campanha_IdAndDataHoraBetween(idCampanha, inicio, fim))
                .contribuicoesRecurso(contribuicaoRecursoRepository.countContribuicaoRecursoByRecurso_Campanha_IdAndDataHoraBetween(idCampanha, inicio, fim))
                .contribuicoesServicos(contribuicaoServicoRepository.countContribuicaoServicoByEventoCampanha_Campanha_IdAndDataHoraBetween(idCampanha, inicio, fim))
                .data(LocalDateTime.now())
                .build();
    }

    public List<Relatorio> buscarTodosRelatorios() {
        return relatorioRepository.findAll();
    }

    public Relatorio buscarRelatorioPorId(UUID id) {
        return relatorioRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Relatorio"));
    }

}

