package collectiva.org.collecta.domain.contribuicao.contribuicaoServico.service;

import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.ContribuicaoServico;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.repository.ContribuicaoServicoRepository;
import collectiva.org.collecta.domain.eventoCampanha.EventoCampanha;
import collectiva.org.collecta.enums.StatusContribuicao;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContribuicaoServicoService {
    private final ContribuicaoServicoRepository contribuicaoServicoRepository;

    public List<ContribuicaoServico> buscarTodasContribuicoesServicos() {
        return contribuicaoServicoRepository.findAll();
    }

    public ContribuicaoServico buscarContribuicaoServicoPorId(UUID id) {
        return contribuicaoServicoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("ContribuicaoServico"));

    }

    public ContribuicaoServico criarContribuicaoServico(ContribuicaoServico contribuicaoServico, Doador doador, EventoCampanha eventoCampanha) {
        contribuicaoServico.setDoador(doador);
        contribuicaoServico.setEventoCampanha(eventoCampanha);
        return contribuicaoServicoRepository.save(contribuicaoServico);
    }

    public ContribuicaoServico atualizarStatusContribuicao(UUID id, StatusContribuicao statusContribuicao) {
        ContribuicaoServico contribuicaoServico = buscarContribuicaoServicoPorId(id);
        contribuicaoServico.setStatusContribuicao(statusContribuicao);
        return contribuicaoServicoRepository.save(contribuicaoServico);
    }

}

