package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.ContribuicaoMonetaria;
import collectiva.org.collecta.dto.ContribuicaoMonetariaDTO;
import collectiva.org.collecta.mapper.ContribuicaoMonetariaMapper;
import collectiva.org.collecta.repository.ContribuicaoMonetariaRepository;
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
public class ContribuicaoMonetariaService {
    private final ContribuicaoMonetariaRepository contribuicaoMonetariaRepository;

    public ResponseEntity<ContribuicaoMonetariaDTO> salvarContribuicaoMonetaria(ContribuicaoMonetariaDTO contribuicaoMonetariaDTO) {
        ContribuicaoMonetaria contribuicaoMonetaria = ContribuicaoMonetariaMapper.paraEntidade(contribuicaoMonetariaDTO);
        contribuicaoMonetariaRepository.save(contribuicaoMonetaria);
        contribuicaoMonetariaDTO = ContribuicaoMonetariaMapper.paraDTO(contribuicaoMonetaria);
        return ResponseEntity.status(HttpStatus.CREATED).body(contribuicaoMonetariaDTO);
    }

    public ResponseEntity<List<ContribuicaoMonetariaDTO>> buscarTodasContribuicoesMonetarias() {
        List<ContribuicaoMonetaria> contribuicaoMonetaria = contribuicaoMonetariaRepository.findAll();
        if (contribuicaoMonetaria.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ContribuicaoMonetariaDTO> contribuicaoMonetariaDTOs = contribuicaoMonetaria.stream().map(ContribuicaoMonetariaMapper::paraDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(contribuicaoMonetariaDTOs);
    }

    public ResponseEntity<ContribuicaoMonetariaDTO> buscarContribuicaoMonetariaPorId(UUID id) {
        Optional<ContribuicaoMonetaria> contribuicaoMonetaria = contribuicaoMonetariaRepository.findById(id);
        if (contribuicaoMonetaria.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ContribuicaoMonetariaDTO contribuicaoMonetariaDTO = ContribuicaoMonetariaMapper.paraDTO(contribuicaoMonetaria.get());
        return ResponseEntity.ok().body(contribuicaoMonetariaDTO);
    }

    public ResponseEntity<ContribuicaoMonetariaDTO> atualizarContribuicaoMonetaria(UUID id, ContribuicaoMonetariaDTO contribuicaoMonetariaDTO) {
        Optional<ContribuicaoMonetaria> contribuicaoMonetariaAntiga = contribuicaoMonetariaRepository.findById(id);
        if (contribuicaoMonetariaAntiga.isPresent()) {
            ContribuicaoMonetaria contribuicaoMonetaria = ContribuicaoMonetariaMapper.paraEntidade(contribuicaoMonetariaDTO);
            contribuicaoMonetaria.setId(contribuicaoMonetariaAntiga.get().getId());
            contribuicaoMonetariaDTO.setId(contribuicaoMonetariaAntiga.get().getId());

            contribuicaoMonetariaRepository.save(contribuicaoMonetaria);
            return ResponseEntity.ok().body(contribuicaoMonetariaDTO);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> deletarContribuicaoMonetaria(UUID id) {
        if (!contribuicaoMonetariaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        contribuicaoMonetariaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

