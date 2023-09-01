package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID uuid;

    private String formaPagamento;
    private int parcelas;

    @OneToOne
    @JoinColumn(name = "doacao")
    private Doacao doacao;
}
