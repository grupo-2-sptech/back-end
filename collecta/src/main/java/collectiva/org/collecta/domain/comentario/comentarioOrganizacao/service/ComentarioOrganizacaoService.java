package collectiva.org.collecta.domain.comentario.comentarioOrganizacao.service;

import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.ComentarioOrganizacao;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.repository.ComentarioOrganizacaoRepository;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComentarioOrganizacaoService {
    private final ComentarioOrganizacaoRepository comentarioOrganizacaoRepository;

    public ComentarioOrganizacao salvarComentario(ComentarioOrganizacao comentarioOrganizacao) {
        return comentarioOrganizacaoRepository.save(comentarioOrganizacao);
    }

    public List<ComentarioOrganizacao> buscarTodosComentarios() {
        return comentarioOrganizacaoRepository.findAll();
    }

    public ComentarioOrganizacao buscarComentarioPorId(UUID id) {
        return comentarioOrganizacaoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("ComentariosOrganizacao"));
    }

    public ComentarioOrganizacao atualizarComentario(UUID id, ComentarioOrganizacao comentarioOrganizacao) {
        buscarComentarioPorId(id);
        comentarioOrganizacao.setId(id);
        return comentarioOrganizacaoRepository.save(comentarioOrganizacao);
    }

    public void deletarComentario(UUID id) {
        if (!comentarioOrganizacaoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("ComentarioOrganizacao");
        }
        comentarioOrganizacaoRepository.deleteById(id);
    }
}

