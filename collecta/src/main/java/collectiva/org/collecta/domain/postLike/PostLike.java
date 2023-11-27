package collectiva.org.collecta.domain.postLike;

import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.postCampanha.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostLike {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private LocalDateTime datahora;

    @ManyToOne
    private Doador doador;

    @ManyToOne
    private Post post;
}
