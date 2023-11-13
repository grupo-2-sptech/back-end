package collectiva.org.collecta.domain.contribuicao.contribuicaoServico.service;

import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.ContribuicaoServico;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.repository.ContribuicaoServicoRepository;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContribuicaoServicoService {
    private final ContribuicaoServicoRepository contribuicaoServicoRepository;

    public ContribuicaoServico salvarContribuicaoServico(ContribuicaoServico contribuicaoServico) {
        return contribuicaoServicoRepository.save(contribuicaoServico);
    }

    public List<ContribuicaoServico> buscarTodasContribuicoesServicos() {
        return contribuicaoServicoRepository.findAll();
    }

    public ContribuicaoServico buscarContribuicaoServicoPorId(UUID id) {
        return contribuicaoServicoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("ContribuicaoServico"));

    }

    public ContribuicaoServico atualizarContribuicaoServico(UUID id, ContribuicaoServico contribuicaoServico) {
        buscarContribuicaoServicoPorId(id);
        contribuicaoServico.setId(id);
        return contribuicaoServicoRepository.save(contribuicaoServico);
    }

    public void deletarContribuicaoServico(UUID id) {
        if (!contribuicaoServicoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("ContribuicaoServico");
        }
        contribuicaoServicoRepository.deleteById(id);
    }
}

