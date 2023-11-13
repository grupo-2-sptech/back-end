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

    private LocalDateTime data;
    private BigDecimal valorArrecadado;
    private int quantidadeFinanceirosCampanha;

    @ManyToOne
    private Campanha campanha;

}