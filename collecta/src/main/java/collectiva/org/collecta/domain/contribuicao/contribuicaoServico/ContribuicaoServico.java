package collectiva.org.collecta.domain.contribuicao.contribuicaoServico;

import collectiva.org.collecta.domain.contribuicao.Contribuicao;
import collectiva.org.collecta.domain.conta.Doador.Doador;
import collectiva.org.collecta.domain.eventoCampanha.EventoCampanha;
import collectiva.org.collecta.enums.StatusContribuicao;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContribuicaoServico extends Contribuicao {

    private LocalDateTime horaInicio;
    private LocalDateTime horaFim;
    private Integer avaliacao;

    @ManyToOne
    private Doador doador;

    @ManyToOne
    private EventoCampanha eventoCampanha;

    @Builder
    public ContribuicaoServico(UUID id, String nome, String descricao, LocalDateTime dataHora, StatusContribuicao statusContribuicao, LocalDateTime horaInicio, LocalDateTime horaFim, Integer avaliacao, Doador doador, EventoCampanha eventoCampanha) {
        super(id, nome, descricao, dataHora, statusContribuicao);
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.avaliacao = avaliacao;
        this.doador = doador;
        this.eventoCampanha = eventoCampanha;
    }
}
