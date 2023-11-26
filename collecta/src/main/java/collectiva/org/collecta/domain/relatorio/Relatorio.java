package collectiva.org.collecta.domain.relatorio;

import collectiva.org.collecta.domain.campanha.Campanha;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Relatorio {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;
    private BigDecimal valorMeta;
    private BigDecimal valorArrecadado;
    private BigDecimal valorRestante;
    private int visualizacoes;
    private int contribuicoesMonetarias;
    private int contribuicoesRecurso;
    private int contribuicoesServicos;
    private LocalDateTime data;

    @ManyToOne
    private Campanha campanha;

}