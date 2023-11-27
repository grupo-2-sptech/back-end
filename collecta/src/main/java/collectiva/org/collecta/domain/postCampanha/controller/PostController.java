package collectiva.org.collecta.domain.postCampanha.controller;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.campanha.dto.ResponseCampanhaDTO;
import collectiva.org.collecta.domain.campanha.mapper.CampanhaMapper;
import collectiva.org.collecta.domain.campanha.service.CampanhaService;
import collectiva.org.collecta.domain.postCampanha.Post;
import collectiva.org.collecta.domain.postCampanha.dto.AssociationPostDTO;
import collectiva.org.collecta.domain.postCampanha.dto.CreatePostDTO;
import collectiva.org.collecta.domain.postCampanha.dto.ResponsePostDTO;
import collectiva.org.collecta.domain.postCampanha.dto.UpdatePostDTO;
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
    private final CampanhaService campanhaService;

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

    @GetMapping("/campanha/{id}")
    public ResponseEntity<List<ResponsePostDTO>> buscarPostPorCampanhaId(@PathVariable UUID id) {
        List<ResponsePostDTO> listaDTO = postService.buscarPostsPorCampanha(id).stream().map(PostMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }


    @PostMapping
    public ResponseEntity<AssociationPostDTO> criarPost(@RequestBody @Valid CreatePostDTO postDTO) {
        Campanha campanha = campanhaService.buscarCampanhaPorId(postDTO.getIdCampanha());
        Post post = postService.salvarPost(PostMapper.paraEntidade(postDTO), campanha);
        return ResponseEntity.status(201).body(PostMapper.paraAssociacaoDTO(post));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationPostDTO> atualizarPost(@PathVariable UUID id, @RequestBody @Valid UpdatePostDTO postDTO) {
        Post post = postService.atualizarPost(id, PostMapper.paraEntidadeUpdate(postDTO));
        return ResponseEntity.ok(PostMapper.paraAssociacaoDTO(post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPost(@PathVariable UUID id) {
        postService.deletarPost(id);
        return ResponseEntity.noContent().build();
    }
}
