package collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.service;

import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.ContribuicaoMonetaria;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.repository.ContribuicaoMonetariaRepository;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContribuicaoMonetariaService {
    private final ContribuicaoMonetariaRepository contribuicaoMonetariaRepository;

    public ContribuicaoMonetaria salvarContribuicaoMonetaria(ContribuicaoMonetaria contribuicaoMonetaria, Doador doador) {
        contribuicaoMonetaria.setDoador(doador);
        return contribuicaoMonetariaRepository.save(contribuicaoMonetaria);
    }

    public List<ContribuicaoMonetaria> buscarTodasContribuicoesMonetarias() {
        return contribuicaoMonetariaRepository.findAll();
    }

    public ContribuicaoMonetaria buscarContribuicaoMonetariaPorId(UUID id) {
        return contribuicaoMonetariaRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("ContribuicaoMonetaria"));
    }

}

