package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.ComentarioOrganizacao;
import collectiva.org.collecta.repository.ComentarioOrganizacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComentarioOrganizacaoService {
    private final ComentarioOrganizacaoRepository comentarioOrganizacaoRepository;

    public ResponseEntity<ComentarioOrganizacao> salvarComentario(ComentarioOrganizacao comentarioDoador) {
        comentarioOrganizacaoRepository.save(comentarioDoador);
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioDoador);
    }

    public ResponseEntity<List<ComentarioOrganizacao>> buscarTodosComentarios() {
        List<ComentarioOrganizacao> comentarioDoador = comentarioOrganizacaoRepository.findAll();
        return ResponseEntity.ok().body(comentarioDoador);
    }

    public ResponseEntity<Optional<ComentarioOrganizacao>> buscarComentarioPorId(UUID id) {
        Optional<ComentarioOrganizacao> comentarioDoador = comentarioOrganizacaoRepository.findById(id);
        return ResponseEntity.ok().body(comentarioDoador);
    }

    public ResponseEntity<ComentarioOrganizacao> atualizarComentario(UUID id, ComentarioOrganizacao comentarioDoador) {
        Optional<ComentarioOrganizacao> comentarioDoadorAntigo = comentarioOrganizacaoRepository.findById(id);
        ComentarioOrganizacao comentarioDoadorExistente = comentarioDoadorAntigo.get();

        ComentarioOrganizacao comentarioDoadorAtualizado = ComentarioOrganizacao.builder()
                .id(comentarioDoadorExistente.getId())
                .comentario(comentarioDoador.getComentario())
                .data(comentarioDoador.getData())
                .build();

        comentarioOrganizacaoRepository.save(comentarioDoadorAtualizado);

        return ResponseEntity.ok().body(comentarioDoadorAtualizado);
    }

    public ResponseEntity<Void> deletarComentario(UUID id) {
        if (!comentarioOrganizacaoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        comentarioOrganizacaoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

