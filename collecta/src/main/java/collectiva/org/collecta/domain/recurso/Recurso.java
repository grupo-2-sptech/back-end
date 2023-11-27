package collectiva.org.collecta.domain.recurso;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.ContribuicaoRecurso;
import collectiva.org.collecta.enums.TipoRecurso;
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
    private Campanha campanha;

    @OneToMany(mappedBy = "recurso")
    private List<ContribuicaoRecurso> contribuicaoRecursos = new ArrayList();
}
