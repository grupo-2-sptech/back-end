package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Post;
import collectiva.org.collecta.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public ResponseEntity<Post> salvarPost(Post post) {
        postRepository.salvarPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    public ResponseEntity<List<Post>> buscarTodosPosts() {
        List<Post> post = postRepository.buscarTodosPosts();
        return ResponseEntity.ok().body(post);
    }

    public ResponseEntity<Post> buscarPostPorId(UUID id) {
        Post post = postRepository.buscarPostPorId(id);
        return ResponseEntity.ok().body(post);
    }
    public ResponseEntity<Post> atualizarPost(UUID id, Post post){
        Post postNovo = postRepository.atualizarPost(id,post);
        return ResponseEntity.ok().body(postNovo);
    }
    public ResponseEntity<Void> deletarPost(UUID id){
        postRepository.excluirPost(id);
        return ResponseEntity.ok().build();
    }
}
