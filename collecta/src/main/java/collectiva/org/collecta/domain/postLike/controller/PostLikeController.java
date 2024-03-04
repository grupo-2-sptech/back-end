package collectiva.org.collecta.domain.postLike.controller;

import collectiva.org.collecta.domain.postLike.dto.ResponsePostLikeDTO;
import collectiva.org.collecta.domain.postLike.service.PostLikeServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/postLikes")
@RequiredArgsConstructor
public class PostLikeController {
    private final PostLikeServiceFacade postLikeServiceF;

    @PostMapping("/{postID}/{doadorID}")
    public ResponseEntity<Void> adicionarPostLike(@PathVariable UUID postID, @PathVariable UUID doadorID) {
        postLikeServiceF.adicionarPostLike(postID, doadorID);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{postID}/{doadorID}")
    public ResponseEntity<Void> removerPostLike(@PathVariable UUID postID, @PathVariable UUID doadorID) {
        postLikeServiceF.removerPostLike(postID, doadorID);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{postID}")
    public ResponseEntity<Integer> contarPostLikesPost(@PathVariable UUID postID) {
        return ResponseEntity.ok(postLikeServiceF.contarPostLikesPost(postID));
    }

    @GetMapping("/post/{postID}")
    public ResponseEntity<List<ResponsePostLikeDTO>> buscarPostLikesPost(@PathVariable UUID postID) {
        List<ResponsePostLikeDTO> listaDTO = postLikeServiceF.buscarPostLikesPost(postID);
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/doador/{doadorID}")
    public ResponseEntity<List<ResponsePostLikeDTO>> buscarPostLikesDoador(@PathVariable UUID doadorID) {
        List<ResponsePostLikeDTO> listaDTO = postLikeServiceF.buscarPostLikesDoador(doadorID);
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

}
