package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Campanha;
import collectiva.org.collecta.dto.CampanhaDTO;
import collectiva.org.collecta.mapper.CampanhaMapper;
import collectiva.org.collecta.repository.CampanhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampanhaService {
    private final CampanhaRepository campanhaRepository;

    public ResponseEntity<CampanhaDTO> salvarCampanha(CampanhaDTO campanhaDTO) {
        Campanha campanha = CampanhaMapper.paraEntidade(campanhaDTO);
        campanhaRepository.save(campanha);
        campanhaDTO = CampanhaMapper.paraDTO(campanha);
        return ResponseEntity.status(HttpStatus.CREATED).body(campanhaDTO);
    }

    public ResponseEntity<List<CampanhaDTO>> buscarTodasCampanhas() {
        List<Campanha> campanha = campanhaRepository.findAll();
        if (campanha.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<CampanhaDTO> campanhaDTOs = campanha.stream().map(CampanhaMapper::paraDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(campanhaDTOs);
    }

    public ResponseEntity<CampanhaDTO> buscarCampanhaPorId(UUID id) {
        Optional<Campanha> campanha = campanhaRepository.findById(id);
        if (campanha.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        CampanhaDTO campanhaDTO = CampanhaMapper.paraDTO(campanha.get());
        return ResponseEntity.ok().body(campanhaDTO);
    }

    public ResponseEntity<CampanhaDTO> atualizarCampanha(UUID id, CampanhaDTO campanhaDTO) {
        Optional<Campanha> campanhaAntiga = campanhaRepository.findById(id);
        if (campanhaAntiga.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Campanha campanhaExistente = campanhaAntiga.get();
        Campanha campanhaAtualizada = Campanha.builder()
                .id(campanhaExistente.getId())
                .nome(campanhaDTO.getNome())
                .descricao(campanhaDTO.getDescricao())
                .dataInicio(campanhaDTO.getDataInicio())
                .dataFim(campanhaDTO.getDataFim())
                .status(campanhaDTO.getStatus())
                .build();

        campanhaRepository.save(campanhaAtualizada);
        campanhaDTO = CampanhaMapper.paraDTO(campanhaAtualizada);
        return ResponseEntity.ok().body(campanhaDTO);
    }

    public ResponseEntity<Void> deletarCampanha(UUID id) {
        if (!campanhaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        campanhaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

