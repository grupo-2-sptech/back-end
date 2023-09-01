package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.ComentarioDoador;
import collectiva.org.collecta.domain.ComentarioOrganizacao;
import collectiva.org.collecta.repository.ComentarioOrganizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ComentarioOrganizacaoService {
    @Autowired
    ComentarioOrganizacaoRepository comentarioOrganizacaoRepository;

    public ResponseEntity<ComentarioOrganizacao> salvarComentario(ComentarioOrganizacao comentarioOrganizacao) {
        comentarioOrganizacaoRepository.salvarComentario(comentarioOrganizacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioOrganizacao);
    }

    public ResponseEntity<List<ComentarioOrganizacao>> buscarTodosComentarios() {
        List<ComentarioOrganizacao> comentariosOrganizacao = comentarioOrganizacaoRepository.buscarTodosComentarios();
        return ResponseEntity.ok().body(comentariosOrganizacao);
    }

    public ResponseEntity<ComentarioOrganizacao> buscarComentarioPorId(UUID id) {
        ComentarioOrganizacao comentarioOrganizacao = comentarioOrganizacaoRepository.buscarComentarioPorId(id);
        return ResponseEntity.ok().body(comentarioOrganizacao);
    }
    public ResponseEntity<ComentarioOrganizacao> atualizadoComentario(UUID id, ComentarioOrganizacao comentarioOrganizacao){
        ComentarioOrganizacao comentarioOrganizacaoNovo = comentarioOrganizacaoRepository.atualizarComentario(id,comentarioOrganizacao);
        return ResponseEntity.ok().body(comentarioOrganizacaoNovo);
    }
    public ResponseEntity<Void> deletarComentario(UUID id){
        comentarioOrganizacaoRepository.excluirComentario(id);
        return ResponseEntity.ok().build();
    }
}
