package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.ComentarioDoador;
import collectiva.org.collecta.repository.ComentarioDoadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComentarioDoadorService {
    private final ComentarioDoadorRepository comentarioDoadorRepository;

    public ResponseEntity<ComentarioDoador> salvarComentario(ComentarioDoador comentarioDoador) {
        comentarioDoadorRepository.save(comentarioDoador);
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioDoador);
    }

    public ResponseEntity<List<ComentarioDoador>> buscarTodosComentarios() {
        List<ComentarioDoador> comentarioDoador = comentarioDoadorRepository.findAll();
        return ResponseEntity.ok().body(comentarioDoador);
    }

    public ResponseEntity<Optional<ComentarioDoador>> buscarComentarioPorId(UUID id) {
        Optional<ComentarioDoador> comentarioDoador = comentarioDoadorRepository.findById(id);
        return ResponseEntity.ok().body(comentarioDoador);
    }

    public ResponseEntity<ComentarioDoador> atualizarComentario(UUID id, ComentarioDoador comentarioDoador) {
        Optional<ComentarioDoador> comentarioDoadorAntigo = comentarioDoadorRepository.findById(id);
        ComentarioDoador comentarioDoadorExistente = comentarioDoadorAntigo.get();

        ComentarioDoador comentarioDoadorAtualizado = ComentarioDoador.builder()
                .id(comentarioDoadorExistente.getId())
                .comentario(comentarioDoador.getComentario())
                .data(comentarioDoador.getData())
                .build();

        comentarioDoadorRepository.save(comentarioDoadorAtualizado);

        return ResponseEntity.ok().body(comentarioDoadorAtualizado);
    }

    public ResponseEntity<Void> deletarComentario(UUID id) {
        comentarioDoadorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

