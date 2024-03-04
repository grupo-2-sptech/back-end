package collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.service;

import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.ContribuicaoRecurso;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.repository.ContribuicaoRecursoRepository;
import collectiva.org.collecta.domain.recurso.Recurso;
import collectiva.org.collecta.domain.recurso.service.RecursoService;
import collectiva.org.collecta.enums.StatusContribuicao;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContribuicaoRecursoService {
    private final ContribuicaoRecursoRepository contribuicaoRecursoRepository;

    public List<ContribuicaoRecurso> buscarTodasContribuicoesRecursos() {
        return contribuicaoRecursoRepository.findAll();
    }

    public ContribuicaoRecurso buscarContribuicaoRecursoPorId(UUID id) {
        return contribuicaoRecursoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("ContribuicaoRecurso"));
    }

    public ContribuicaoRecurso criarContribuicaoRecurso(ContribuicaoRecurso contribuicaoRecurso, Doador doador, Recurso recurso) {
        contribuicaoRecurso.setDoador(doador);
        contribuicaoRecurso.setRecurso(recurso);
        return contribuicaoRecursoRepository.save(contribuicaoRecurso);
    }

    public ContribuicaoRecurso atualizarStatusContribuicao(UUID id, StatusContribuicao statusContribuicao) {
        ContribuicaoRecurso contribuicaoRecurso = buscarContribuicaoRecursoPorId(id);
        contribuicaoRecurso.setStatusContribuicao(statusContribuicao);
        return contribuicaoRecursoRepository.save(contribuicaoRecurso);
    }
}

