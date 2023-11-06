package collectiva.org.collecta.domain.postCampanha;

import collectiva.org.collecta.domain.campanha.Campanha;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "campanha")
    private Campanha campanha;

}