package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Campanha;
import collectiva.org.collecta.dto.CampanhaDTO;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoContemElementosException;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import collectiva.org.collecta.mapper.CampanhaMapper;
import collectiva.org.collecta.repository.CampanhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampanhaService {
    private final CampanhaRepository campanhaRepository;

    public CampanhaDTO salvarCampanha(CampanhaDTO campanhaDTO) {
        Campanha campanha = CampanhaMapper.paraEntidade(campanhaDTO);
        campanhaRepository.save(campanha);
        return CampanhaMapper.paraDTO(campanha);
    }

    public List<CampanhaDTO> buscarTodasCampanhas() {
        List<Campanha> campanha = campanhaRepository.findAll();
        if (campanha.isEmpty()) {
            throw new EntidadeNaoContemElementosException("Campanha");
        }
        return campanha.stream().map(CampanhaMapper::paraDTO).collect(Collectors.toList());
    }

    public CampanhaDTO buscarCampanhaPorId(UUID id) {
        return CampanhaMapper.paraDTO(campanhaRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Campanha")));
    }

    public CampanhaDTO atualizarCampanha(UUID id, CampanhaDTO campanhaDTO) {
        buscarCampanhaPorId(id);
        Campanha campanhaNova = CampanhaMapper.paraEntidade(campanhaDTO);
        campanhaNova.setId(id);
        campanhaRepository.save(campanhaNova);
        return CampanhaMapper.paraDTO(campanhaNova);
    }

    public void deletarCampanha(UUID id) {
        if (!campanhaRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Campanha");
        }
        campanhaRepository.deleteById(id);
    }
}

