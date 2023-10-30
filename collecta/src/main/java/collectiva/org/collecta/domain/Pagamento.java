package collectiva.org.collecta.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    private String numeroCartao;
    private String dataValidade;
    private String codigoSeguranca;
    private String bandeiraCartao;

}
