package collectiva.org.collecta.domain.postCampanha.mapper;

import collectiva.org.collecta.domain.postCampanha.Post;
import collectiva.org.collecta.domain.postCampanha.dto.CreatePostDTO;
import collectiva.org.collecta.domain.postCampanha.dto.ResponsePostDTO;

import java.time.LocalDateTime;

public class PostMapper {
    private PostMapper(){}

    public static Post paraEntidade(CreatePostDTO postDTO){
        return Post.builder()
                .titulo(postDTO.getTitulo())
                .conteudo(postDTO.getConteudo())
                .data(LocalDateTime.now())
                .build();
    }

    public static ResponsePostDTO paraDTO(Post post){
        return ResponsePostDTO.builder()
                .id(post.getId())
                .titulo(post.getTitulo())
                .conteudo(post.getConteudo())
                .data(post.getData())
                .build();
    }

}
