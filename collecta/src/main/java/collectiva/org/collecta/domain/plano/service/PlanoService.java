package collectiva.org.collecta.domain.plano.service;

import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.plano.Plano;
import collectiva.org.collecta.domain.plano.repository.PlanoRepository;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlanoService {
    private final PlanoRepository planoRepository;

    public Plano salvarPlano(Plano plano, Doador doador) {
        plano.setDoador(doador);
        return planoRepository.save(plano);
    }

    public List<Plano> buscarTodosPlanos() {
        return planoRepository.findAll();
    }

    public Plano buscarPlanoPorId(UUID id) {
        return planoRepository.findById(id).orElseThrow(()
                -> new EntidadeNaoEncontradaException("Plano"));
    }

    public Plano atualizarPlano(UUID id, Plano plano) {
        buscarPlanoPorId(id);
        plano.setId(id);
        return planoRepository.save(plano);
    }

    public void deletarPlano(UUID id) {
        if (!planoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Plano");
        }
        planoRepository.deleteById(id);
    }
}

