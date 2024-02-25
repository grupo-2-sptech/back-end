package collectiva.org.collecta.domain.comentario.comentarioDoador.service;

import collectiva.org.collecta.domain.comentario.comentarioDoador.ComentarioDoador;
import collectiva.org.collecta.domain.comentario.comentarioDoador.dto.AssociationComentarioDoadorDTO;
import collectiva.org.collecta.domain.comentario.comentarioDoador.dto.CreateComentarioDoadorDTO;
import collectiva.org.collecta.domain.comentario.comentarioDoador.dto.ResponseComentarioDoadorDTO;
import collectiva.org.collecta.domain.comentario.comentarioDoador.mapper.ComentarioDoadorMapper;
import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.conta.doador.service.DoadorService;
import collectiva.org.collecta.domain.postCampanha.Post;
import collectiva.org.collecta.domain.postCampanha.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComentarioDoadorServiceFacade {
    private final ComentarioDoadorService comentarioDoadorService;
    private final DoadorService doadorService;
    private final PostService postService;

    public List<ResponseComentarioDoadorDTO> buscarTodosComentarios() {
        return comentarioDoadorService.buscarTodosComentarios().stream().map
                (ComentarioDoadorMapper::paraDTO).toList();
    }

    public ResponseComentarioDoadorDTO buscarComentarioPorId(UUID id) {
        return ComentarioDoadorMapper.paraDTO(comentarioDoadorService.buscarComentarioPorId(id));
    }

    public AssociationComentarioDoadorDTO criarComentario(UUID idDoador, UUID idPost, CreateComentarioDoadorDTO comentarioDoadorDTO) {
        Doador doador = doadorService.buscarDoadorPorId(idDoador);
        Post post = postService.buscarPostPorId(idPost);
        ComentarioDoador comentarioDoador = ComentarioDoadorMapper.paraEntidade(comentarioDoadorDTO);
        return ComentarioDoadorMapper.paraAssociacaoDTO
                (comentarioDoadorService.criarComentario(comentarioDoador, doador, post));
    }

    public void deletarComentario(UUID id) {
        comentarioDoadorService.deletarComentario(id);
    }

}
