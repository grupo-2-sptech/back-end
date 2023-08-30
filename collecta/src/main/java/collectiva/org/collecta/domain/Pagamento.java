package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Data
public class Pagamento {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private UUID uuid;

    private String formaPagamento;
    private int parcelas;

    @OneToOne
    @JoinColumn(name = "doacao")
    private Doacao doacao;
}
