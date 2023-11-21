package collectiva.org.collecta.domain.postCampanha.controller;

import collectiva.org.collecta.domain.postCampanha.Post;
import collectiva.org.collecta.domain.postCampanha.dto.CreatePostDTO;
import collectiva.org.collecta.domain.postCampanha.dto.ResponsePostDTO;
import collectiva.org.collecta.domain.postCampanha.mapper.PostMapper;
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
    public ResponseEntity<List<ResponsePostDTO>> buscarPosts() {
        List<ResponsePostDTO> listaDTO = postService.buscarTodosPosts().stream()
                .map(PostMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePostDTO> buscarPostPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(PostMapper.paraDTO(postService.buscarPostPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ResponsePostDTO> criarPost(@RequestBody @Valid CreatePostDTO postDTO) {
        Post post = postService.salvarPost(PostMapper.paraEntidade(postDTO));
        return ResponseEntity.status(201).body(PostMapper.paraDTO(post));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsePostDTO> atualizarPost(@PathVariable UUID id, @RequestBody @Valid CreatePostDTO postDTO) {
        Post post = postService.atualizarPost(id, PostMapper.paraEntidade(postDTO));
        return ResponseEntity.ok(PostMapper.paraDTO(post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPost(@PathVariable UUID id) {
        postService.deletarPost(id);
        return ResponseEntity.noContent().build();
    }
}
