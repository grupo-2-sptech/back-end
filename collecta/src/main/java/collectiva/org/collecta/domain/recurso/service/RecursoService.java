package collectiva.org.collecta.domain.recurso.service;

import collectiva.org.collecta.domain.recurso.Recurso;
import collectiva.org.collecta.domain.recurso.repository.RecursoRepository;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecursoService {
    private final RecursoRepository recursoRepository;

    public Recurso salvarRecurso(Recurso recurso) {
        return recursoRepository.save(recurso);
    }

    public List<Recurso> buscarTodosRecursos() {
        return recursoRepository.findAll();
    }

    public Recurso buscarRecursoPorId(UUID id) {
        return recursoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Recurso"));
    }

    public Recurso atualizarRecurso(UUID id, Recurso recurso) {
        buscarRecursoPorId(id);
        recurso.setId(id);
        return recursoRepository.save(recurso);
    }

    public void deletarRecurso(UUID id) {
        if (!recursoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Recurso");
        }
        recursoRepository.deleteById(id);
    }
}

