package collectiva.org.collecta.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Relatorio {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    private Date data;
    private BigDecimal valorArrecadado;
    private int quantidadeDoacoes;

}