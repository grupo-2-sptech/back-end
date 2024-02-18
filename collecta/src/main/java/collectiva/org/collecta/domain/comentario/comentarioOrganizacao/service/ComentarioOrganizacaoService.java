package collectiva.org.collecta.domain.comentario.comentarioOrganizacao.service;

import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.ComentarioOrganizacao;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.dto.AssociationComentarioOrganizacaoDTO;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.dto.CreateComentarioOrganizacaoDTO;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.dto.ResponseComentarioOrganizacaoDTO;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.mapper.ComentarioOrganizacaoMapper;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.repository.ComentarioOrganizacaoRepository;
import collectiva.org.collecta.domain.conta.organizacao.Organizacao;
import collectiva.org.collecta.domain.conta.organizacao.service.OrganizacaoService;
import collectiva.org.collecta.domain.postCampanha.Post;
import collectiva.org.collecta.domain.postCampanha.service.PostService;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComentarioOrganizacaoService {
    private final ComentarioOrganizacaoRepository comentarioOrganizacaoRepository;
    private final OrganizacaoService organizacaoService;
    private final PostService postService;

    public ComentarioOrganizacao buscarExisteComentario(UUID id) {
        return comentarioOrganizacaoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("ComentariosOrganizacao"));
    }

    public List<ResponseComentarioOrganizacaoDTO> buscarTodosComentarios() {
        return comentarioOrganizacaoRepository.findAll().stream()
                .map(ComentarioOrganizacaoMapper::paraDTO).toList();
    }

    public ResponseComentarioOrganizacaoDTO buscarComentarioPorId(UUID id) {
        return ComentarioOrganizacaoMapper.paraDTO(buscarExisteComentario(id));
    }

    public AssociationComentarioOrganizacaoDTO salvarComentario(CreateComentarioOrganizacaoDTO comentarioOrganizacaoDTO, UUID idOrganizacao, UUID idPost) {
        Organizacao organizacao = organizacaoService.buscarOrganizacaoPorId(idOrganizacao);
        Post post = postService.buscarPostPorId(idPost);
        ComentarioOrganizacao comentarioOrganizacao = ComentarioOrganizacaoMapper.paraEntidade(comentarioOrganizacaoDTO);
        comentarioOrganizacao.setOrganizacao(organizacao);
        comentarioOrganizacao.setPost(post);
        return ComentarioOrganizacaoMapper.paraAssociacaoDTO(comentarioOrganizacaoRepository.save(comentarioOrganizacao));
    }

    public void deletarComentario(UUID id) {
        buscarExisteComentario(id);
        comentarioOrganizacaoRepository.deleteById(id);
    }
}

