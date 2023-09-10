package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.ComentarioDoador;
import collectiva.org.collecta.dto.ComentarioDoadorDTO;
import collectiva.org.collecta.mapper.ComentarioDoadorMapper;
import collectiva.org.collecta.repository.ComentarioDoadorRepository;
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
public class ComentarioDoadorService {
    private final ComentarioDoadorRepository comentarioDoadorRepository;

    public ResponseEntity<ComentarioDoadorDTO> salvarComentario(ComentarioDoadorDTO comentarioDoadorDTO) {
        ComentarioDoador comentarioDoador = ComentarioDoadorMapper.paraEntidade(comentarioDoadorDTO);
        comentarioDoadorRepository.save(comentarioDoador);
        comentarioDoadorDTO =ComentarioDoadorMapper.paraDTO(comentarioDoador);
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioDoadorDTO);
    }

    public ResponseEntity<List<ComentarioDoadorDTO>> buscarTodosComentarios() {
        List<ComentarioDoador> comentarioDoador = comentarioDoadorRepository.findAll();
        if (comentarioDoador.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ComentarioDoadorDTO> comentarioDoadoresDTO = comentarioDoador.stream().map(ComentarioDoadorMapper::paraDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(comentarioDoadoresDTO);
    }


    public ResponseEntity<ComentarioDoadorDTO> buscarComentarioPorId(UUID id) {
        Optional<ComentarioDoador> comentarioDoador = comentarioDoadorRepository.findById(id);
        if (comentarioDoador.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ComentarioDoadorDTO comentarioDoadorDTO = ComentarioDoadorMapper.paraDTO(comentarioDoador.get());
        return ResponseEntity.ok().body(comentarioDoadorDTO);
    }

    public ResponseEntity<ComentarioDoadorDTO> atualizarComentario(UUID id, ComentarioDoadorDTO comentarioDoadorDTO) {
        Optional<ComentarioDoador> comentarioDoadorAntigo = comentarioDoadorRepository.findById(id);
        if (comentarioDoadorAntigo.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ComentarioDoador comentarioDoadorExistente = comentarioDoadorAntigo.get();
        ComentarioDoador comentarioDoadorAtualizado = ComentarioDoador.builder()
                .id(comentarioDoadorExistente.getId())
                .comentario(comentarioDoadorDTO.getComentario())
                .data(comentarioDoadorDTO.getData())
                .build();

        comentarioDoadorRepository.save(comentarioDoadorAtualizado);
        comentarioDoadorDTO = ComentarioDoadorMapper.paraDTO(comentarioDoadorAtualizado);
        return ResponseEntity.ok().body(comentarioDoadorDTO);
    }

    public ResponseEntity<Void> deletarComentario(UUID id) {
        if (!comentarioDoadorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        comentarioDoadorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
