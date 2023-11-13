package collectiva.org.collecta.domain.postCampanha.service;

import collectiva.org.collecta.domain.postCampanha.Post;
import collectiva.org.collecta.domain.postCampanha.repository.PostRepository;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post salvarPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> buscarTodosPosts() {
        return postRepository.findAll();
    }

    public Post buscarPostPorId(UUID id) {
        return postRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Post"));
    }

    public Post atualizarPost(UUID id, Post post) {
        buscarPostPorId(id);
        post.setId(id);
        return postRepository.save(post);
    }

    public void deletarPost(UUID id) {
        if (!postRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Post");
        }
        postRepository.deleteById(id);
    }
}

