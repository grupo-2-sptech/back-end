package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String titulo;
    private String conteudo;
    private Date data;

    @ManyToOne
    @JoinColumn(name = "campanha")
    private Campanha campanha;

    public Post() {
    }
}