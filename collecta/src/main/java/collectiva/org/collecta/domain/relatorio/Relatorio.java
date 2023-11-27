package collectiva.org.collecta.domain.relatorio;

import collectiva.org.collecta.domain.acaoCampanha.AcaoCampanha;
import collectiva.org.collecta.domain.campanha.Campanha;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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
    private Long contribuicoesMonetarias;
    private Long contribuicoesRecurso;
    private Long contribuicoesServicos;
    private LocalDateTime data;

    @ManyToOne
    private Campanha campanha;
    
    @OneToMany(mappedBy = "relatorio")
    List<AcaoCampanha> acoes;
}