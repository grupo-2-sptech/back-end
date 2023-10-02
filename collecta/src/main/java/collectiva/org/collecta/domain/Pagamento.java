package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String formaPagamento;
    private int parcelas;

    @OneToOne
    @JoinColumn(name = "doacao")
    private FinanceiroCampanha doacao;

}
