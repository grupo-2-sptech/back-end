package collectiva.org.collecta.domain;

import collectiva.org.collecta.domain.enums.TipoConta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Comentario {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String comentario;
    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;

    @ManyToOne
    @JoinColumn(name = "post")
    private Post post;

}