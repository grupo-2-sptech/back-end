package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.ContribuicaoMonetaria;
import collectiva.org.collecta.dto.ContribuicaoMonetariaDTO;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoContemElementosException;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import collectiva.org.collecta.mapper.ContribuicaoMonetariaMapper;
import collectiva.org.collecta.repository.ContribuicaoMonetariaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContribuicaoMonetariaService {
    private final ContribuicaoMonetariaRepository contribuicaoMonetariaRepository;

    public ContribuicaoMonetariaDTO salvarContribuicaoMonetaria(ContribuicaoMonetariaDTO contribuicaoMonetariaDTO) {
        ContribuicaoMonetaria contribuicaoMonetaria = ContribuicaoMonetariaMapper.paraEntidade(contribuicaoMonetariaDTO);
        contribuicaoMonetariaRepository.save(contribuicaoMonetaria);
        return ContribuicaoMonetariaMapper.paraDTO(contribuicaoMonetaria);
    }

    public List<ContribuicaoMonetariaDTO> buscarTodasContribuicoesMonetarias() {
        List<ContribuicaoMonetaria> contribuicaoMonetaria = contribuicaoMonetariaRepository.findAll();
        if (contribuicaoMonetaria.isEmpty()) {
            throw new EntidadeNaoContemElementosException("ContribuicaoMonetaria");
        }
        return contribuicaoMonetaria.stream().map(ContribuicaoMonetariaMapper::paraDTO).collect(Collectors.toList());
    }

    public ContribuicaoMonetariaDTO buscarContribuicaoMonetariaPorId(UUID id) {
        return ContribuicaoMonetariaMapper.paraDTO(contribuicaoMonetariaRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("ContribuicaoMonetaria")));
    }

    public ContribuicaoMonetariaDTO atualizarContribuicaoMonetaria(UUID id, ContribuicaoMonetariaDTO contribuicaoMonetariaDTO) {
        buscarContribuicaoMonetariaPorId(id);
        ContribuicaoMonetaria contribuicaoMonetariaNova = ContribuicaoMonetariaMapper.paraEntidade(contribuicaoMonetariaDTO);
        contribuicaoMonetariaNova.setId(id);
        contribuicaoMonetariaRepository.save(contribuicaoMonetariaNova);
        return ContribuicaoMonetariaMapper.paraDTO(contribuicaoMonetariaNova);
    }

    public void deletarContribuicaoMonetaria(UUID id) {
        if (!contribuicaoMonetariaRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("ContribuicaoMonetaria");
        }
        contribuicaoMonetariaRepository.deleteById(id);
    }
}

