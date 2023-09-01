package collectiva.org.collecta.controller;

import collectiva.org.collecta.domain.Post;
import collectiva.org.collecta.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> buscarPosts() {
        return postService.buscarTodosPosts();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Post> buscarPostPorId(@PathVariable UUID id) {
        return postService.buscarPostPorId(id);
    }

    @PostMapping
    public ResponseEntity<Post> criarPost(@RequestBody Post post) {
        return postService.salvarPost(post);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Post> atualizarPost(@PathVariable UUID id, @RequestBody Post post) {
        return postService.atualizarPost(id, post);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPost(@PathVariable UUID id){
        return  postService.deletarPost(id);
    }
}
