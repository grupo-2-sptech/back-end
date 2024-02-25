package collectiva.org.collecta.domain.comentario.comentarioOrganizacao.service;

import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.ComentarioOrganizacao;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.dto.AssociationComentarioOrganizacaoDTO;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.dto.CreateComentarioOrganizacaoDTO;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.dto.ResponseComentarioOrganizacaoDTO;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.mapper.ComentarioOrganizacaoMapper;
import collectiva.org.collecta.domain.conta.organizacao.Organizacao;
import collectiva.org.collecta.domain.conta.organizacao.service.OrganizacaoService;
import collectiva.org.collecta.domain.postCampanha.Post;
import collectiva.org.collecta.domain.postCampanha.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComentarioOrganizacaoServiceFacade {
    private final ComentarioOrganizacaoService comentarioOrganizacaoService;
    private final OrganizacaoService organizacaoService;
    private final PostService postService;

    public List<ResponseComentarioOrganizacaoDTO> buscarTodosComentarios() {
        return comentarioOrganizacaoService.buscarTodosComentarios().stream().map
                (ComentarioOrganizacaoMapper::paraDTO).toList();
    }

    public ResponseComentarioOrganizacaoDTO buscarComentarioPorId(UUID id) {
        return ComentarioOrganizacaoMapper.paraDTO(comentarioOrganizacaoService.buscarComentarioPorId(id));
    }

    public AssociationComentarioOrganizacaoDTO criarComentario(UUID idOrganizacao, UUID idPost, CreateComentarioOrganizacaoDTO comentarioOrganizacaoDTO) {
        Organizacao organizacao = organizacaoService.buscarOrganizacaoPorId(idOrganizacao);
        Post post = postService.buscarPostPorId(idPost);
        ComentarioOrganizacao comentarioOrganizacao = ComentarioOrganizacaoMapper.paraEntidade(comentarioOrganizacaoDTO);
        return ComentarioOrganizacaoMapper.paraAssociacaoDTO
                (comentarioOrganizacaoService.criarComentario(comentarioOrganizacao, organizacao, post));
    }

    public void deletarComentario(UUID id) {
        comentarioOrganizacaoService.deletarComentario(id);
    }

}
