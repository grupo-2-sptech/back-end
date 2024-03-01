package collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.service;

import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.ContribuicaoMonetaria;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.repository.ContribuicaoMonetariaRepository;
import collectiva.org.collecta.domain.financeiroCampanha.FinanceiroCampanha;
import collectiva.org.collecta.domain.financeiroCampanha.service.FinanceiroCampanhaService;
import collectiva.org.collecta.enums.StatusContribuicao;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContribuicaoMonetariaService {
    private final ContribuicaoMonetariaRepository contribuicaoMonetariaRepository;
    private final FinanceiroCampanhaService financeiroCampanhaService;

    public List<ContribuicaoMonetaria> buscarTodasContribuicoesMonetarias() {
        return contribuicaoMonetariaRepository.findAll();
    }

    public ContribuicaoMonetaria buscarContribuicaoMonetariaPorId(UUID id) {
        return contribuicaoMonetariaRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("ContribuicaoMonetaria"));
    }

    public ContribuicaoMonetaria criarContribuicaoMonetaria (ContribuicaoMonetaria contribuicaoMonetaria, Doador doador, FinanceiroCampanha financeiroCampanha) {
        contribuicaoMonetaria.setFinanceiroCampanha(financeiroCampanha);
        contribuicaoMonetaria.setDoador(doador);
        if (contribuicaoMonetaria.getStatusContribuicao().equals(StatusContribuicao.FINALIZADA)){
            financeiroCampanhaService.somarContribuicao(financeiroCampanha, contribuicaoMonetaria.getValor());
        }
        return contribuicaoMonetariaRepository.save(contribuicaoMonetaria);
    }

    public ContribuicaoMonetaria atualizarStatusContribuicao(UUID id, StatusContribuicao statusContribuicao) {
        ContribuicaoMonetaria contribuicaoMonetaria = buscarContribuicaoMonetariaPorId(id);
        contribuicaoMonetaria.setStatusContribuicao(statusContribuicao);
        if (contribuicaoMonetaria.getStatusContribuicao().equals(StatusContribuicao.FINALIZADA)){
            financeiroCampanhaService.somarContribuicao(contribuicaoMonetaria.getFinanceiroCampanha(), contribuicaoMonetaria.getValor());
        }
        return contribuicaoMonetariaRepository.save(contribuicaoMonetaria);
    }

}

