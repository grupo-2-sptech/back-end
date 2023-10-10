package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Post;
import collectiva.org.collecta.dto.PostDTO;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoContemElementosException;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import collectiva.org.collecta.mapper.PostMapper;
import collectiva.org.collecta.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostDTO salvarPost(PostDTO postDTO) {
        Post post = PostMapper.paraEntidade(postDTO);
        postRepository.save(post);
        return PostMapper.paraDTO(post);
    }

    public List<PostDTO> buscarTodosPosts() {
        List<Post> posts = postRepository.findAll();
        if (posts.isEmpty()) {
            throw new EntidadeNaoContemElementosException("Post");
        }
        return posts.stream().map(PostMapper::paraDTO).collect(Collectors.toList());
    }

    public PostDTO buscarPostPorId(UUID id) {
        return PostMapper.paraDTO(postRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Post")));
    }

    public PostDTO atualizarPost(UUID id, PostDTO postDTO) {
        buscarPostPorId(id);
        Post postNovo = PostMapper.paraEntidade(postDTO);
        postNovo.setId(id);
        postRepository.save(postNovo);
        return PostMapper.paraDTO(postNovo);
    }

    public void deletarPost(UUID id) {
        if (!postRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Post");
        }
        postRepository.deleteById(id);
    }
}

