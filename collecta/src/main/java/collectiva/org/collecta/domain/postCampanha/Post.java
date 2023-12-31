package collectiva.org.collecta.domain.postCampanha;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.comentario.Comentario;
import collectiva.org.collecta.domain.postLike.PostLike;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String titulo;
    private String conteudo;
    private String urlImagem;
    private LocalDateTime data;

    @OneToMany(mappedBy = "post")
    private List<PostLike> postLikes;

    @OneToMany(mappedBy = "post")
    private List<Comentario> comentarios;

    @ManyToOne
    private Campanha campanha;

}