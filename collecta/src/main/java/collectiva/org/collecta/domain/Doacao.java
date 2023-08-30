package collectiva.org.collecta.domain;

import collectiva.org.collecta.domain.enums.ModoContribuição;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Doacao {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue( generator = "uuid")
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

    @OneToOne(mappedBy = "doacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private Pagamento pagamento;
}
