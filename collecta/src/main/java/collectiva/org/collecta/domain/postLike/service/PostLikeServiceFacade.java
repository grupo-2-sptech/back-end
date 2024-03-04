package collectiva.org.collecta.domain.postLike.service;

import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.conta.doador.service.DoadorService;
import collectiva.org.collecta.domain.postCampanha.Post;
import collectiva.org.collecta.domain.postCampanha.service.PostService;
import collectiva.org.collecta.domain.postLike.dto.ResponsePostLikeDTO;
import collectiva.org.collecta.domain.postLike.mapper.PostLikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostLikeServiceFacade {
    private final PostLikeService postLikeService;
    private final DoadorService doadorService;
    private final PostService postService;

    public void adicionarPostLike(UUID postID, UUID doadorID) {
        Doador doador = doadorService.buscarDoadorPorId(doadorID);
        Post post = postService.buscarPostPorId(postID);
        postLikeService.adicionarPostLike(doador, post);
    }

    public void removerPostLike(UUID postID, UUID doadorID) {
        Doador doador = doadorService.buscarDoadorPorId(doadorID);
        Post post = postService.buscarPostPorId(postID);
        postLikeService.removerPostLike(doador, post);
    }

    public Integer contarPostLikesPost(UUID postID) {
        Post post = postService.buscarPostPorId(postID);
        return postLikeService.contarPostLikesPost(post);
    }

    public List<ResponsePostLikeDTO> buscarPostLikesPost(UUID postID) {
        Post post = postService.buscarPostPorId(postID);
        return postLikeService.buscarPostLikesPost(post).stream().map(PostLikeMapper::paraRespostaDTO).toList();
    }

    public List<ResponsePostLikeDTO> buscarPostLikesDoador(UUID doadorID) {
        Doador doador = doadorService.buscarDoadorPorId(doadorID);
        return postLikeService.buscarPostLikesDoador(doador).stream().map(PostLikeMapper::paraRespostaDTO).toList();
    }


}
