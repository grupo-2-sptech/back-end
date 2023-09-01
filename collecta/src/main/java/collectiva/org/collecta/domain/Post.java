package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    private String titulo;
    private String conteudo;
    private Date data;

    @ManyToOne
    @JoinColumn(name = "campanha")
    private Campanha campanha;
}