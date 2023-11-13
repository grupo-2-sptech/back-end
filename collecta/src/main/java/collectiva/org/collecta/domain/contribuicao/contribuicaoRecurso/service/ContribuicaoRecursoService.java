package collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.service;

import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.ContribuicaoRecurso;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.repository.ContribuicaoRecursoRepository;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContribuicaoRecursoService {
    private final ContribuicaoRecursoRepository contribuicaoRecursoRepository;

    public ContribuicaoRecurso salvarContribuicaoRecurso(ContribuicaoRecurso contribuicaoRecurso) {
        return contribuicaoRecursoRepository.save(contribuicaoRecurso);
    }

    public List<ContribuicaoRecurso> buscarTodasContribuicoesRecursos() {
        return contribuicaoRecursoRepository.findAll();
    }

    public ContribuicaoRecurso buscarContribuicaoRecursoPorId(UUID id) {
        return contribuicaoRecursoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("ContribuicaoRecurso"));
    }

    public ContribuicaoRecurso atualizarContribuicaoRecurso(UUID id, ContribuicaoRecurso contribuicaoRecurso) {
        buscarContribuicaoRecursoPorId(id);
        contribuicaoRecurso.setId(id);
        return contribuicaoRecursoRepository.save(contribuicaoRecurso);
    }

    public void deletarContribuicaoRecurso(UUID id) {
        if (!contribuicaoRecursoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("ContribuicaoRecurso");
        }
        contribuicaoRecursoRepository.deleteById(id);
    }
}

