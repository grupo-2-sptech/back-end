package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Post;
import collectiva.org.collecta.dto.PostDTO;
import collectiva.org.collecta.mapper.PostMapper;
import collectiva.org.collecta.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public ResponseEntity<PostDTO> salvarPost(PostDTO postDTO) {
        Post post = PostMapper.paraEntidade(postDTO);
        postRepository.save(post);
        postDTO = PostMapper.paraDTO(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(postDTO);
    }

    public ResponseEntity<List<PostDTO>> buscarTodosPosts() {
        List<Post> posts = postRepository.findAll();
        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<PostDTO> postsDTO = posts.stream().map(PostMapper::paraDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(postsDTO);
    }

    public ResponseEntity<PostDTO> buscarPostPorId(UUID id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            return ResponseEntity.notFound().build();

        }
        PostDTO postDTO = PostMapper.paraDTO(post.get());
        return ResponseEntity.ok().body(postDTO);
    }

    public ResponseEntity<PostDTO> atualizarPost(UUID id, PostDTO postDTO) {
        Optional<Post> postAntigo = postRepository.findById(id);
        if (postAntigo.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Post postExistente = postAntigo.get();
        Post postAtualizado = Post.builder()
                .id(postExistente.getId())
                .titulo(postDTO.getTitulo())
                .conteudo(postDTO.getConteudo())
                .data(postExistente.getData())
                .build();

        postRepository.save(postAtualizado);
        postDTO = PostMapper.paraDTO(postAtualizado);
        return ResponseEntity.ok().body(postDTO);
    }

    public ResponseEntity<Void> deletarPost(UUID id) {
        if (!postRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        postRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

