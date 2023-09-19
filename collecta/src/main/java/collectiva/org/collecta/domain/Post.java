package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
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