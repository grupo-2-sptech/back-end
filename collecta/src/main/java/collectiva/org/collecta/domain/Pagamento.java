package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    private String formaPagamento;
    private int parcelas;

    @OneToOne
    @JoinColumn(name = "doacao")
    private Doacao doacao;

    public Pagamento() {

    }
}
