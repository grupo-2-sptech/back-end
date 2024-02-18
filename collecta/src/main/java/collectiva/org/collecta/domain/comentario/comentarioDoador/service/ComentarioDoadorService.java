package collectiva.org.collecta.domain.comentario.comentarioDoador.service;

import collectiva.org.collecta.domain.comentario.comentarioDoador.ComentarioDoador;
import collectiva.org.collecta.domain.comentario.comentarioDoador.dto.AssociationComentarioDoadorDTO;
import collectiva.org.collecta.domain.comentario.comentarioDoador.dto.CreateComentarioDoadorDTO;
import collectiva.org.collecta.domain.comentario.comentarioDoador.dto.ResponseComentarioDoadorDTO;
import collectiva.org.collecta.domain.comentario.comentarioDoador.mapper.ComentarioDoadorMapper;
import collectiva.org.collecta.domain.comentario.comentarioDoador.repository.ComentarioDoadorRepository;
import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.conta.doador.service.DoadorService;
import collectiva.org.collecta.domain.postCampanha.Post;
import collectiva.org.collecta.domain.postCampanha.service.PostService;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComentarioDoadorService {
    private final ComentarioDoadorRepository comentarioDoadorRepository;
    private final DoadorService doadorService;
    private final PostService postService;

    public ComentarioDoador buscarExisteComentario(UUID id) {
        return comentarioDoadorRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("ComentarioDoador"));
    }

    public List<ResponseComentarioDoadorDTO> buscarTodosComentarios() {
        return comentarioDoadorRepository.findAll().stream().map
                (ComentarioDoadorMapper::paraDTO).toList();
    }

    public ResponseComentarioDoadorDTO buscarComentarioPorId(UUID id) {
        return ComentarioDoadorMapper.paraDTO(buscarExisteComentario(id));
    }

    public AssociationComentarioDoadorDTO criarComentario(CreateComentarioDoadorDTO comentarioDoadorDTO, UUID idDoador, UUID idPost) {
        Doador doador = doadorService.buscarDoadorPorId(idDoador);
        Post post = postService.buscarPostPorId(idPost);
        ComentarioDoador comentarioDoador = ComentarioDoadorMapper.paraEntidade(comentarioDoadorDTO);
        comentarioDoador.setDoador(doador);
        comentarioDoador.setPost(post);
        return ComentarioDoadorMapper.paraAssociacaoDTO(comentarioDoadorRepository.save(comentarioDoador));
    }

    public void deletarComentario(UUID id) {
        buscarExisteComentario(id);
        comentarioDoadorRepository.deleteById(id);
    }
}
