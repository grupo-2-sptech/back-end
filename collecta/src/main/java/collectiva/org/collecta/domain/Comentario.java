package collectiva.org.collecta.domain;

import collectiva.org.collecta.domain.enums.TipoConta;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
public abstract class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    private String comentario;
    private Date data;

    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;

    @ManyToOne
    @JoinColumn(name = "post")
    private Post post;

}