package collectiva.org.collecta.domain.postLike.mapper;

import collectiva.org.collecta.domain.postLike.PostLike;
import collectiva.org.collecta.domain.postLike.dto.ResponsePostLikeDTO;

public class PostLikeMapper {
    private PostLikeMapper() {
    }

    public static ResponsePostLikeDTO paraRespostaDTO(PostLike postLike){
        return ResponsePostLikeDTO.builder()
                .id(postLike.getId())
                .email(postLike.getDoador().getEmail())
                .postId(postLike.getPost().getId())
                .postTitulo(postLike.getPost().getTitulo())
                .build();
    }
}
