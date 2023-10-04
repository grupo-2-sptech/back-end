package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.ContribuicaoRecurso;
import collectiva.org.collecta.dto.ContribuicaoRecursoDTO;
import collectiva.org.collecta.mapper.ContribuicaoRecursoMapper;
import collectiva.org.collecta.repository.ContribuicaoRecursoRepository;
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
public class ContribuicaoRecursoService {
    private final ContribuicaoRecursoRepository contribuicaoRecursoRepository;

    public ResponseEntity<ContribuicaoRecursoDTO> salvarContribuicaoRecurso(ContribuicaoRecursoDTO contribuicaoRecursoDTO) {
        ContribuicaoRecurso contribuicaoRecurso = ContribuicaoRecursoMapper.paraEntidade(contribuicaoRecursoDTO);
        contribuicaoRecursoRepository.save(contribuicaoRecurso);
        contribuicaoRecursoDTO = ContribuicaoRecursoMapper.paraDTO(contribuicaoRecurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(contribuicaoRecursoDTO);
    }

    public ResponseEntity<List<ContribuicaoRecursoDTO>> buscarTodasContribuicoesRecursos() {
        List<ContribuicaoRecurso> contribuicaoRecurso = contribuicaoRecursoRepository.findAll();
        if (contribuicaoRecurso.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ContribuicaoRecursoDTO> contribuicaoRecursoDTOs = contribuicaoRecurso.stream().map(ContribuicaoRecursoMapper::paraDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(contribuicaoRecursoDTOs);
    }

    public ResponseEntity<ContribuicaoRecursoDTO> buscarContribuicaoRecursoPorId(UUID id) {
        Optional<ContribuicaoRecurso> contribuicaoRecurso = contribuicaoRecursoRepository.findById(id);
        if (contribuicaoRecurso.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ContribuicaoRecursoDTO contribuicaoRecursoDTO = ContribuicaoRecursoMapper.paraDTO(contribuicaoRecurso.get());
        return ResponseEntity.ok().body(contribuicaoRecursoDTO);
    }

    public ResponseEntity<ContribuicaoRecursoDTO> atualizarContribuicaoRecurso(UUID id, ContribuicaoRecursoDTO contribuicaoRecursoDTO) {
        Optional<ContribuicaoRecurso> contribuicaoRecursoAntiga = contribuicaoRecursoRepository.findById(id);
        if (contribuicaoRecursoAntiga.isPresent()) {
            ContribuicaoRecurso contribuicaoRecurso = ContribuicaoRecursoMapper.paraEntidade(contribuicaoRecursoDTO);
            contribuicaoRecurso.setId(contribuicaoRecursoAntiga.get().getId());
            contribuicaoRecursoDTO.setId(contribuicaoRecursoAntiga.get().getId());

            contribuicaoRecursoRepository.save(contribuicaoRecurso);
            return ResponseEntity.ok().body(contribuicaoRecursoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> deletarContribuicaoRecurso(UUID id) {
        if (!contribuicaoRecursoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        contribuicaoRecursoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

