package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Post;
import collectiva.org.collecta.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public ResponseEntity<Post> salvarPost(Post post) {
        postRepository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    public ResponseEntity<List<Post>> buscarTodosPosts() {
        List<Post> post = postRepository.findAll();
        if (post.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(post);
    }

    public ResponseEntity<Optional<Post>> buscarPostPorId(UUID id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok().body(post);
    }

    public ResponseEntity<Post> atualizarPost(UUID id, Post post) {
        Optional<Post> postAntigo = postRepository.findById(id);
        if (postAntigo.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Post postExistente = postAntigo.get();
        Post postAtualizado = Post.builder()
                .id(postExistente.getId())
                .titulo(post.getTitulo())
                .conteudo(post.getConteudo())
                .data(post.getData())
                .build();

        postRepository.save(postAtualizado);

        return ResponseEntity.ok().body(postAtualizado);
    }

    public ResponseEntity<Void> deletarPost(UUID id) {
        if (postRepository.existsById(id)) {
            return ResponseEntity.badRequest().build();
        }
        postRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

