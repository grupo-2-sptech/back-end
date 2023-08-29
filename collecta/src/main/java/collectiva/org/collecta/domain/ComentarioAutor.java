package collectiva.org.collecta.domain;

import collectiva.org.collecta.domain.enums.TipoConta;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class ComentarioAutor {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Conta conta;

    @OneToMany
    @JoinColumn(name = "comentario")
    private List<Comentario> comentario = new ArrayList<Comentario>();
}
