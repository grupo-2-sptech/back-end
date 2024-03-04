package collectiva.org.collecta.domain.recurso.service;

import collectiva.org.collecta.domain.campanha.Campanha;
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

    public List<Recurso> buscarTodosRecursos() {
        return recursoRepository.findAll();
    }

    public Recurso buscarRecursoPorId(UUID id) {
        return recursoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Recurso"));
    }

    public Recurso criarRecurso(Recurso recurso, Campanha campanha) {
        recurso.setCampanha(campanha);
        return recursoRepository.save(recurso);
    }

    public Recurso atualizarRecurso(UUID id, Recurso recurso) {
        Recurso recursoNovo = buscarRecursoPorId(id);
        recursoNovo.setCampanha(recurso.getCampanha());
        recursoNovo.setContribuicaoRecursos(recurso.getContribuicaoRecursos());
        return recursoRepository.save(recurso);
    }

    public void deletarRecurso(UUID id) {
        if (!recursoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Recurso");
        }
        recursoRepository.deleteById(id);
    }

    public void somarContribuicao(Recurso recurso, int valor) {
        int total = recurso.getQuantidadeArrecadada() + valor;
        recurso.setQuantidadeArrecadada(total);
        recursoRepository.save(recurso);
    }
}

