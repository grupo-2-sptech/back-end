package collectiva.org.collecta.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Pagamento {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID uuid;
    private String formaPagamento;
    private int parcelas;
}
