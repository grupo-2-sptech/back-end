package collectiva.org.collecta.domain.postCampanha.mapper;

import collectiva.org.collecta.domain.comentario.comentario.ComentarioMapper;
import collectiva.org.collecta.domain.postCampanha.Post;
import collectiva.org.collecta.domain.postCampanha.dto.AssociationPostDTO;
import collectiva.org.collecta.domain.postCampanha.dto.CreatePostDTO;
import collectiva.org.collecta.domain.postCampanha.dto.ResponsePostDTO;
import collectiva.org.collecta.domain.postCampanha.dto.UpdatePostDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class PostMapper {
    private PostMapper() {
    }

    public static Post paraEntidade(CreatePostDTO postDTO) {
        return Post.builder()
                .titulo(postDTO.getTitulo())
                .conteudo(postDTO.getConteudo())
                .postLikes(new ArrayList<>())
                .data(LocalDateTime.now())
                .urlImagem(postDTO.getUrlImagem())
                .build();
    }

    public static Post paraEntidadeUpdate(UpdatePostDTO postDTO) {
        return Post.builder()
                .titulo(postDTO.getTitulo())
                .conteudo(postDTO.getConteudo())
                .data(LocalDateTime.now())
                .build();
    }

    public static ResponsePostDTO paraDTO(Post post) {
        return ResponsePostDTO.builder()
                .id(post.getId())
                .titulo(post.getTitulo())
                .conteudo(post.getConteudo())
                .data(post.getData())
                .likes(post.getPostLikes().size())
                .comentarios(post.getComentarios().stream().map(ComentarioMapper::paraAssociacaoDTO).toList())
                .urlImagem(post.getUrlImagem())
                .build();
    }

    public static AssociationPostDTO paraAssociacaoDTO(Post post) {
        return AssociationPostDTO.builder()
                .id(post.getId())
                .titulo(post.getTitulo())
                .conteudo(post.getConteudo())
                .likes(Optional.ofNullable(post.getPostLikes().size()).orElse(0))
                .data(post.getData())
                .urlImagem(post.getUrlImagem())
                .build();
    }
}
