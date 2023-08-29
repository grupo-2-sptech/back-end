package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Relatorio {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private Date data;
    private BigDecimal valorArrecadado;
    private int quantidadeDoacoes;

    @ManyToOne
    @JoinColumn(name = "campanha")
    private Campanha campanha;

}