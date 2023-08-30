package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Post {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String titulo;
    private String conteudo;
    private Date data;

    @ManyToOne
    @JoinColumn(name = "campanha")
    private Campanha campanha;
}