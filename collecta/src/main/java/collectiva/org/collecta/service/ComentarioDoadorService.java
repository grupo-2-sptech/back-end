package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.ComentarioDoador;
import collectiva.org.collecta.repository.ComentarioDoadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ComentarioDoadorService {
    @Autowired
    ComentarioDoadorRepository comentarioDoadorRepository;

    public ResponseEntity<ComentarioDoador> salvarComentario(ComentarioDoador comentarioDoador) {
        comentarioDoadorRepository.salvarComentario(comentarioDoador);
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioDoador);
    }

    public ResponseEntity<List<ComentarioDoador>> buscarTodosComentarios() {
        List<ComentarioDoador> comentarioDoadors = comentarioDoadorRepository.buscarTodosComentarios();
        return ResponseEntity.ok().body(comentarioDoadors);
    }

    public ResponseEntity<ComentarioDoador> buscarComentarioPorId(UUID id) {
        ComentarioDoador comentarioDoador = comentarioDoadorRepository.buscarComentarioPorId(id);
        return ResponseEntity.ok().body(comentarioDoador);
    }
    public ResponseEntity<ComentarioDoador> atualizadoComentario(UUID id, ComentarioDoador comentarioDoador){
        ComentarioDoador comentario = comentarioDoadorRepository.atualizarComentario(id,comentarioDoador);
        return ResponseEntity.ok().body(comentario);
    }
    public ResponseEntity<Void> deletarComentario(UUID id){
        comentarioDoadorRepository.excluirComentario(id);
        return ResponseEntity.ok().build();
    }
}
