package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Plano;
import collectiva.org.collecta.dto.PlanoDTO;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoContemElementosException;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import collectiva.org.collecta.mapper.PlanoMapper;
import collectiva.org.collecta.repository.PlanoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanoService {
    private final PlanoRepository planoRepository;

    public PlanoDTO salvarPlano(PlanoDTO planoDTO) {
        Plano plano = PlanoMapper.paraEntidade(planoDTO);
        planoRepository.save(plano);
        return PlanoMapper.paraDTO(plano);
    }

    public List<PlanoDTO> buscarTodosPlanos() {
        List<Plano> planos = planoRepository.findAll();
        if (planos.isEmpty()){
            throw new EntidadeNaoContemElementosException("Planos");
        }

        return planos.stream().map(PlanoMapper::paraDTO).collect(Collectors.toList());
    }

    public PlanoDTO buscarPlanoPorId(UUID id) {
        return PlanoMapper.paraDTO(planoRepository.findById(id).orElseThrow(()
                -> new EntidadeNaoEncontradaException("Plano")));
    }

    public PlanoDTO atualizarPlano(UUID id, PlanoDTO planoDTO) {
        buscarPlanoPorId(id);
        Plano planoNovo = PlanoMapper.paraEntidade(planoDTO);
        planoNovo.setId(id);
        planoRepository.save(planoNovo);
        return PlanoMapper.paraDTO(planoNovo);
    }

    public void deletarPlano(UUID id) {
        if (!planoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Plano");
        }
        planoRepository.deleteById(id);
    }
}

