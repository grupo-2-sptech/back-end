package collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.service;

import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.ContribuicaoRecurso;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.dto.ContribuicaoRecursoDTO;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.mapper.ContribuicaoRecursoMapper;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.repository.ContribuicaoRecursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContribuicaoRecursoService {
    private final ContribuicaoRecursoRepository contribuicaoRecursoRepository;

    public ContribuicaoRecursoDTO salvarContribuicaoRecurso(ContribuicaoRecursoDTO contribuicaoRecursoDTO) {
        ContribuicaoRecurso contribuicaoRecurso = ContribuicaoRecursoMapper.paraEntidade(contribuicaoRecursoDTO);
        contribuicaoRecursoRepository.save(contribuicaoRecurso);
        return ContribuicaoRecursoMapper.paraDTO(contribuicaoRecurso);
    }

    public List<ContribuicaoRecursoDTO> buscarTodasContribuicoesRecursos() {
        List<ContribuicaoRecurso> contribuicaoRecurso = contribuicaoRecursoRepository.findAll();
        return contribuicaoRecurso.stream().map(ContribuicaoRecursoMapper::paraDTO).collect(Collectors.toList());
    }

    public ContribuicaoRecursoDTO buscarContribuicaoRecursoPorId(UUID id) {
        return ContribuicaoRecursoMapper.paraDTO(contribuicaoRecursoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("ContribuicaoRecurso")));
    }

    public ContribuicaoRecursoDTO atualizarContribuicaoRecurso(UUID id, ContribuicaoRecursoDTO contribuicaoRecursoDTO) {
        buscarContribuicaoRecursoPorId(id);
        ContribuicaoRecurso contribuicaoRecursoNovo = ContribuicaoRecursoMapper.paraEntidade(contribuicaoRecursoDTO);
        contribuicaoRecursoNovo.setId(id);
        contribuicaoRecursoRepository.save(contribuicaoRecursoNovo);
        return ContribuicaoRecursoMapper.paraDTO(contribuicaoRecursoNovo);
    }

    public void deletarContribuicaoRecurso(UUID id) {
        if (!contribuicaoRecursoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("ContribuicaoRecurso");
        }
        contribuicaoRecursoRepository.deleteById(id);
    }
}

