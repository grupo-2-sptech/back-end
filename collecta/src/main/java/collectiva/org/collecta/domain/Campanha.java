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
public class Campanha {
    @Id
    @GeneratedValue( generator = "uuid")
    private UUID id;
    private String nome;
    private String descricao;
    private BigDecimal valorMeta;
    private BigDecimal valorArrecadado;
    private Date dataInicio;
    private Date dataFim;
    private String status;

}
