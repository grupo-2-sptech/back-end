package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Recurso;
import collectiva.org.collecta.dto.RecursoDTO;
import collectiva.org.collecta.mapper.RecursoMapper;
import collectiva.org.collecta.repository.RecursoRepository;
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
public class RecursoService {
    private final RecursoRepository recursoRepository;

    public ResponseEntity<RecursoDTO> salvarRecurso(RecursoDTO recursoDTO) {
        Recurso recurso = RecursoMapper.paraEntidade(recursoDTO);
        recursoRepository.save(recurso);
        recursoDTO = RecursoMapper.paraDTO(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(recursoDTO);
    }

    public ResponseEntity<List<RecursoDTO>> buscarTodosRecursos() {
        List<Recurso> recursos = recursoRepository.findAll();
        if (recursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<RecursoDTO> recursosDTO = recursos.stream().map(RecursoMapper::paraDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(recursosDTO);
    }

    public ResponseEntity<RecursoDTO> buscarRecursoPorId(UUID id) {
        Optional<Recurso> recurso = recursoRepository.findById(id);
        if (recurso.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        RecursoDTO recursoDTO = RecursoMapper.paraDTO(recurso.get());
        return ResponseEntity.ok().body(recursoDTO);
    }

    public ResponseEntity<RecursoDTO> atualizarRecurso(UUID id, RecursoDTO recursoDTO) {
        Optional<Recurso> recursoAntigo = recursoRepository.findById(id);
        if (recursoAntigo.isPresent()) {
            Recurso recurso = RecursoMapper.paraEntidade(recursoDTO);
            recurso.setId(recursoAntigo.get().getId());
            recursoDTO.setId(recursoAntigo.get().getId());
            recursoRepository.save(recurso);
            return ResponseEntity.ok().body(recursoDTO);
        }

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> deletarRecurso(UUID id) {
        if (!recursoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        recursoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

