package collectiva.org.collecta.domain.postLike.controller;

import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.conta.doador.service.DoadorService;
import collectiva.org.collecta.domain.postLike.dto.RespostaPostLikeDTO;
import collectiva.org.collecta.domain.postLike.mapper.PostLikeMapper;
import collectiva.org.collecta.domain.postLike.service.PostLikeService;
import collectiva.org.collecta.domain.postCampanha.Post;
import collectiva.org.collecta.domain.postCampanha.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/postLikes")
@RequiredArgsConstructor
public class PostLikeController {
    private final PostLikeService postLikeService;
    private final DoadorService doadorService;
    private final PostService postService;

    @PostMapping("/{postID}/{doadorID}")
    public ResponseEntity<Void> adicionarPostLike(@PathVariable UUID postID, @PathVariable UUID doadorID) {
        Doador doador = doadorService.buscarDoadorPorId(doadorID);
        Post post = postService.buscarPostPorId(postID);
        postLikeService.adicionarPostLike(doador, post);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{postID}/{doadorID}")
    public ResponseEntity<Void> removerPostLike(@PathVariable UUID postID, @PathVariable UUID doadorID) {
        Doador doador = doadorService.buscarDoadorPorId(doadorID);
        Post post = postService.buscarPostPorId(postID);
        postLikeService.removerPostLike(doador, post);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{postID}")
    public ResponseEntity<Integer> contarPostLikesPost(@PathVariable UUID postID) {
        Integer postLikes = postLikeService.contarPostLikesPost(postService.buscarPostPorId(postID));
        return ResponseEntity.ok(postLikes);
    }

    @GetMapping("/post/{postID}")
    public ResponseEntity<List<RespostaPostLikeDTO>> buscarPostLikesPost(@PathVariable UUID postID){
        Post post = postService.buscarPostPorId(postID);
        List<RespostaPostLikeDTO> listaDTO = postLikeService.buscarPostLikesPost(post).stream()
                .map(PostLikeMapper::paraRespostaDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/doador/{doadorID}")
    public ResponseEntity<List<RespostaPostLikeDTO>> buscarPostLikesDoador(@PathVariable UUID doadorID){
        Doador doador = doadorService.buscarDoadorPorId(doadorID);
        List<RespostaPostLikeDTO> listaDTO = postLikeService.buscarPostLikesDoador(doador).stream()
                .map(PostLikeMapper::paraRespostaDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

}
