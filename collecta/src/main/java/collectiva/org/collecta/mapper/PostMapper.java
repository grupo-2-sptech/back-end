package collectiva.org.collecta.mapper;

import collectiva.org.collecta.domain.Post;
import collectiva.org.collecta.dto.PostDTO;

import java.time.LocalDateTime;

public class PostMapper {
    private PostMapper(){}

    public static Post paraEntidade(PostDTO postDTO){
        return Post.builder()
                .titulo(postDTO.getTitulo())
                .conteudo(postDTO.getConteudo())
                .data(LocalDateTime.now())
                .build();
    }

    public static PostDTO paraDTO(Post post){
        return PostDTO.builder()
                .id(post.getId())
                .titulo(post.getTitulo())
                .conteudo(post.getConteudo())
                .data(post.getData())
                .build();
    }

}
