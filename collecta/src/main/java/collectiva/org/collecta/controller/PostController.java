package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.PostDTO;
import collectiva.org.collecta.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDTO>> buscarPosts() {
        return postService.buscarTodosPosts();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> buscarPostPorId(@PathVariable UUID id) {
        return postService.buscarPostPorId(id);
    }

    @PostMapping
    public ResponseEntity<PostDTO> criarPost(@RequestBody @Valid PostDTO postDTO) {
        return postService.salvarPost(postDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> atualizarPost(@PathVariable UUID id, @RequestBody @Valid PostDTO postDTO) {
        return postService.atualizarPost(id, postDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPost(@PathVariable UUID id){
        return  postService.deletarPost(id);
    }
}
