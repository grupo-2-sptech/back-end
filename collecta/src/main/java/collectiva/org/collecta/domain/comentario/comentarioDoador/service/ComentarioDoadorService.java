package collectiva.org.collecta.domain.comentario.comentarioDoador.service;

import collectiva.org.collecta.domain.comentario.comentarioDoador.ComentarioDoador;
import collectiva.org.collecta.domain.comentario.comentarioDoador.repository.ComentarioDoadorRepository;
import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.postCampanha.Post;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComentarioDoadorService {
    private final ComentarioDoadorRepository comentarioDoadorRepository;

    public List<ComentarioDoador> buscarTodosComentarios() {
        return comentarioDoadorRepository.findAll();
    }

    public ComentarioDoador buscarComentarioPorId(UUID id) {
        return comentarioDoadorRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("ComentarioDoador"));
    }

    public ComentarioDoador criarComentario(ComentarioDoador comentarioDoador, Doador doador, Post post) {
        comentarioDoador.setDoador(doador);
        comentarioDoador.setPost(post);
        return comentarioDoadorRepository.save(comentarioDoador);
    }

    public void deletarComentario(UUID id) {
        buscarComentarioPorId(id);
        comentarioDoadorRepository.deleteById(id);
    }
}
