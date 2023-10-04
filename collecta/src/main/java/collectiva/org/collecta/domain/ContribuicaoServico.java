package collectiva.org.collecta.domain;

import collectiva.org.collecta.domain.enums.StatusContribuicao;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class ContribuicaoServico extends Contribuicao{

    private LocalDateTime horaInicio;
    private LocalDateTime horaFim;
    private Integer avaliacao;

    @ManyToOne
    @JoinColumn(name="doador")
    private Doador doador;

    @ManyToOne
    @JoinColumn(name="eventoCampanha")
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
