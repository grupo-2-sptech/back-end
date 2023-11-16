package collectiva.org.collecta.domain.postLike.mapper;

import collectiva.org.collecta.domain.postLike.PostLike;
import collectiva.org.collecta.domain.postLike.dto.RespostaPostLikeDTO;

public class PostLikeMapper {
    private PostLikeMapper() {
    }

    public static RespostaPostLikeDTO paraRespostaDTO(PostLike postLike){
        return RespostaPostLikeDTO.builder()
                .id(postLike.getId())
                .email(postLike.getDoador().getEmail())
                .postId(postLike.getPost().getId())
                .postTitulo(postLike.getPost().getTitulo())
                .build();
    }
}
