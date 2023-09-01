package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Relatorio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    private Date data;
    private BigDecimal valorArrecadado;
    private int quantidadeDoacoes;

    @ManyToOne
    @JoinColumn(name = "campanha")
    private Campanha campanha;

}