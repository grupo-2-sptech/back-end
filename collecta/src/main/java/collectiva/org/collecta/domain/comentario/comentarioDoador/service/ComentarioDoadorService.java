package collectiva.org.collecta.domain.comentario.comentarioDoador.service;

import collectiva.org.collecta.domain.comentario.comentarioDoador.ComentarioDoador;
import collectiva.org.collecta.domain.comentario.comentarioDoador.repository.ComentarioDoadorRepository;
import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComentarioDoadorService {
    private final ComentarioDoadorRepository comentarioDoadorRepository;

    public ComentarioDoador salvarComentario(ComentarioDoador comentarioDoador, Doador doador) {
        comentarioDoador.setDoador(doador);
        return comentarioDoadorRepository.save(comentarioDoador);
    }

    public List<ComentarioDoador> buscarTodosComentarios() {
        return comentarioDoadorRepository.findAll();
    }

    public ComentarioDoador buscarComentarioPorId(UUID id) {
        return comentarioDoadorRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("ComentarioDoador"));
    }

    public ComentarioDoador atualizarComentario(UUID id, ComentarioDoador comentarioDoador) {
        buscarComentarioPorId(id);
        comentarioDoador.setId(id);
        return comentarioDoadorRepository.save(comentarioDoador);
    }

    public void deletarComentario(UUID id) {
        if (!comentarioDoadorRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("ComentarioDoador");
        }
        comentarioDoadorRepository.deleteById(id);
    }
}
