package collectiva.org.collecta.domain.postLike.service;

import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.postCampanha.Post;
import collectiva.org.collecta.domain.postLike.PostLike;
import collectiva.org.collecta.domain.postLike.repository.PostLikeRepository;
import collectiva.org.collecta.exception.exceptions.ConflitoEntidadeException;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;

    public List<PostLike> buscarPostLikesDoador(Doador doador) {
        return postLikeRepository.findByDoador(doador);
    }

    public List<PostLike> buscarPostLikesPost(Post post) {
        return postLikeRepository.findByPost(post);
    }

    public void adicionarPostLike(Doador doador, Post post) throws ResponseStatusException {
        if (postLikeRepository.existsByDoadorAndPost(doador, post)) {
            throw new ConflitoEntidadeException("Like");
        }
        postLikeRepository.save(PostLike.builder()
                .datahora(LocalDateTime.now())
                .post(post)
                .doador(doador)
                .build());
    }

    @Transactional
    public void removerPostLike(Doador doador, Post post) {
        if (!postLikeRepository.existsByDoadorAndPost(doador, post)) {
            throw new EntidadeNaoEncontradaException("Like");
        }
        postLikeRepository.deleteByDoadorAndPost(doador, post);
    }

    public Integer contarPostLikesPost(Post post) {
        return postLikeRepository.countByPost(post);
    }
}

