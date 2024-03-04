package collectiva.org.collecta.domain.postCampanha.controller;

import collectiva.org.collecta.domain.postCampanha.dto.AssociationPostDTO;
import collectiva.org.collecta.domain.postCampanha.dto.CreatePostDTO;
import collectiva.org.collecta.domain.postCampanha.dto.ResponsePostDTO;
import collectiva.org.collecta.domain.postCampanha.dto.UpdatePostDTO;
import collectiva.org.collecta.domain.postCampanha.service.PostServiceFacade;
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
    private final PostServiceFacade postServiceF;

    @GetMapping
    public ResponseEntity<List<ResponsePostDTO>> buscarPosts() {
        List<ResponsePostDTO> listaDTO = postServiceF.buscarTodosPosts();
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePostDTO> buscarPostPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(postServiceF.buscarPostPorId(id));
    }

    @GetMapping("/campanha/{id}")
    public ResponseEntity<List<ResponsePostDTO>> buscarPostPorCampanhaId(@PathVariable UUID id) {
        List<ResponsePostDTO> listaDTO = postServiceF.buscarPostsPorCampanha(id);
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @PostMapping("/{idCampanha}")
    public ResponseEntity<AssociationPostDTO> criarPost(@PathVariable UUID idCampanha, @RequestBody @Valid CreatePostDTO postDTO) {
        return ResponseEntity.status(201).body(postServiceF.criarPost(idCampanha, postDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationPostDTO> atualizarPost(@PathVariable UUID id, @RequestBody @Valid UpdatePostDTO postDTO) {
        return ResponseEntity.ok(postServiceF.atualizarPost(id, postDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPost(@PathVariable UUID id) {
        postServiceF.deletarPost(id);
        return ResponseEntity.noContent().build();
    }
}
