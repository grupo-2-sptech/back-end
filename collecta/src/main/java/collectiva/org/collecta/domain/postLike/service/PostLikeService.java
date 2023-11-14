package collectiva.org.collecta.domain.postLike.service;

import collectiva.org.collecta.domain.conta.Doador.Doador;
import collectiva.org.collecta.domain.postLike.PostLike;
import collectiva.org.collecta.domain.postLike.repository.PostLikeRepository;
import collectiva.org.collecta.domain.postCampanha.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;

    public void adicionarPostLike(Doador doador, Post post) {
        postLikeRepository.save(PostLike.builder()
                .datahora(LocalDateTime.now())
                .post(post)
                .doador(doador)
                .build());
    }
    public void removerPostLike(Doador doador, Post post) {
        postLikeRepository.deleteByPostAndAndDoador(doador, post);
    }
    
    public Integer contarPostLikesPost(Post post) {
        return postLikeRepository.countByPost(post);
    }

    public List<PostLike> buscarPostLikesDoador(Doador doador){
        return postLikeRepository.findByDoador(doador);
    }

    public List<PostLike> buscarPostLikesPost(Post post){
        return postLikeRepository.findByPost(post);
    }
}

