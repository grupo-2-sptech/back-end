package collectiva.org.collecta.domain.contribuicao.contribuicaoServico.service;

import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.ContribuicaoServico;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.dto.ContribuicaoServicoDTO;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.mapper.ContribuicaoServicoMapper;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.repository.ContribuicaoServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContribuicaoServicoService {
    private final ContribuicaoServicoRepository contribuicaoServicoRepository;

    public ContribuicaoServicoDTO salvarContribuicaoServico(ContribuicaoServicoDTO contribuicaoServicoDTO) {
        ContribuicaoServico contribuicaoServico = ContribuicaoServicoMapper.paraEntidade(contribuicaoServicoDTO);
        contribuicaoServicoRepository.save(contribuicaoServico);
        return ContribuicaoServicoMapper.paraDTO(contribuicaoServico);
    }

    public List<ContribuicaoServicoDTO> buscarTodasContribuicoesServicos() {
        List<ContribuicaoServico> contribuicaoServico = contribuicaoServicoRepository.findAll();
        return contribuicaoServico.stream().map(ContribuicaoServicoMapper::paraDTO).collect(Collectors.toList());
    }

    public ContribuicaoServicoDTO buscarContribuicaoServicoPorId(UUID id) {
        return ContribuicaoServicoMapper.paraDTO(contribuicaoServicoRepository.findById(id).orElseThrow(
                ()-> new EntidadeNaoEncontradaException("ContribuicaoServico")));

    }

    public ContribuicaoServicoDTO atualizarContribuicaoServico(UUID id, ContribuicaoServicoDTO contribuicaoServicoDTO) {
        buscarContribuicaoServicoPorId(id);
        ContribuicaoServico contribuicaoServicoNovo = ContribuicaoServicoMapper.paraEntidade(contribuicaoServicoDTO);
        contribuicaoServicoNovo.setId(id);
        contribuicaoServicoRepository.save(contribuicaoServicoNovo);
        return ContribuicaoServicoMapper.paraDTO(contribuicaoServicoNovo);
    }

    public void deletarContribuicaoServico(UUID id) {
        if (!contribuicaoServicoRepository.existsById(id)) {
           throw new EntidadeNaoEncontradaException("ContribuicaoServico");
        }
        contribuicaoServicoRepository.deleteById(id);

    }
}

