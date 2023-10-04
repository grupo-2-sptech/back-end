package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Plano;
import collectiva.org.collecta.dto.PlanoDTO;
import collectiva.org.collecta.mapper.PlanoMapper;
import collectiva.org.collecta.repository.PlanoRepository;
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
public class PlanoService {
    private final PlanoRepository planoRepository;

    public ResponseEntity<PlanoDTO> salvarPlano(PlanoDTO planoDTO) {
        Plano plano = PlanoMapper.paraEntidade(planoDTO);
        planoRepository.save(plano);
        planoDTO = PlanoMapper.paraDTO(plano);
        return ResponseEntity.status(HttpStatus.CREATED).body(planoDTO);
    }

    public ResponseEntity<List<PlanoDTO>> buscarTodosPlanos() {
        List<Plano> planos = planoRepository.findAll();
        if (planos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<PlanoDTO> planosDTO = planos.stream().map(PlanoMapper::paraDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(planosDTO);
    }

    public ResponseEntity<PlanoDTO> buscarPlanoPorId(UUID id) {
        Optional<Plano> plano = planoRepository.findById(id);
        if (plano.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PlanoDTO planoDTO = PlanoMapper.paraDTO(plano.get());
        return ResponseEntity.ok().body(planoDTO);
    }

    public ResponseEntity<PlanoDTO> atualizarPlano(UUID id, PlanoDTO planoDTO) {
        Optional<Plano> planoAntigo = planoRepository.findById(id);
        if (planoAntigo.isPresent()) {
            Plano plano = PlanoMapper.paraEntidade(planoDTO);
            plano.setId(planoAntigo.get().getId());
            planoDTO.setId(planoAntigo.get().getId());
            planoRepository.save(plano);
            return ResponseEntity.ok().body(planoDTO);
        }

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> deletarPlano(UUID id) {
        if (!planoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        planoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

