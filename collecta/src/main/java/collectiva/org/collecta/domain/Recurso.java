package collectiva.org.collecta.domain;

import collectiva.org.collecta.domain.enums.TipoRecurso;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recurso {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    private String nome;
    private String descricao;
    private int quantidadeArrecadada;
    private int quantidadeMeta;
    private TipoRecurso tipoRecurso;

    @ManyToOne
    @JoinColumn(name = "campanha")
    private Campanha campanha;

    @OneToMany(mappedBy = "recurso")
    private List<ContribuicaoRecurso> contribuicaoRecursos = new ArrayList();
}
