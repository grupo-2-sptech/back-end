package collectiva.org.collecta.domain.postCampanha.controller;

import collectiva.org.collecta.domain.postCampanha.dto.PostDTO;
import collectiva.org.collecta.domain.postCampanha.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDTO>> buscarPosts() {
        List<PostDTO> lista = postService.buscarTodosPosts();
        return ResponseEntity.status(lista.isEmpty() ? 204 : 200).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> buscarPostPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(postService.buscarPostPorId(id));
    }

    @PostMapping
    public ResponseEntity<PostDTO> criarPost(@RequestBody @Valid PostDTO postDTO) {
        return ResponseEntity.status(201).body(postService.salvarPost(postDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> atualizarPost(@PathVariable UUID id, @RequestBody @Valid PostDTO postDTO) {
        return ResponseEntity.ok(postService.atualizarPost(id, postDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPost(@PathVariable UUID id) {
        postService.deletarPost(id);
        return ResponseEntity.noContent().build();
    }
}
