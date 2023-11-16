package collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso;

import collectiva.org.collecta.domain.contribuicao.Contribuicao;
import collectiva.org.collecta.domain.conta.Doador.Doador;
import collectiva.org.collecta.domain.recurso.Recurso;
import collectiva.org.collecta.enums.StatusContribuicao;
import collectiva.org.collecta.enums.TipoRecurso;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContribuicaoRecurso extends Contribuicao {

    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    private TipoRecurso tipoRecurso;

    @ManyToOne
    private Doador doador;

    @ManyToOne
    private Recurso recurso;

    @Builder
    public ContribuicaoRecurso(UUID id, String nome, String descricao, LocalDateTime dataHora, StatusContribuicao statusContribuicao, Integer quantidade, TipoRecurso tipoRecurso, Doador doador, Recurso recurso) {
        super(id, nome, descricao, dataHora, statusContribuicao);
        this.quantidade = quantidade;
        this.tipoRecurso = tipoRecurso;
        this.doador = doador;
        this.recurso = recurso;
    }
}
