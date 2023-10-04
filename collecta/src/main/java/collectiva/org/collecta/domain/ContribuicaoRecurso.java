package collectiva.org.collecta.domain;

import collectiva.org.collecta.domain.enums.StatusContribuicao;
import collectiva.org.collecta.domain.enums.TipoRecurso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContribuicaoRecurso extends Contribuicao{

    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    private TipoRecurso tipoRecurso;

    @ManyToOne
    @JoinColumn(name="doador")
    private Doador doador;

    @ManyToOne
    @JoinColumn(name="recurso")
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
