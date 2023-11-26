package collectiva.org.collecta.domain.pagamento;

import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.ContribuicaoMonetaria;
import collectiva.org.collecta.domain.plano.Plano;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;
    private String nomeTitular;
    private String cpf;
    private String valor;
    private String numeroCartao;
    private String dataValidade;
    private String codigoSeguranca;
    private String bandeiraCartao;

    @OneToOne
    private ContribuicaoMonetaria contribuicaoMonetaria;

}
