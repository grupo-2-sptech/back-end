package collectiva.org.collecta.domain;

import collectiva.org.collecta.domain.enums.ModoContribuição;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Doacao {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private BigDecimal valor;
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private ModoContribuição modoContribuicao;

    @ManyToOne
    @JoinColumn(name = "doador")
    private Doador doador;

    @ManyToOne
    @JoinColumn(name = "campanha")
    private Campanha campanha;

    @OneToOne(mappedBy = "doacao")
    private Pagamento pagamento;

}
