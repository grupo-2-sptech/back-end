package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Relatorio {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private Date data;
    private BigDecimal valorArrecadado;
    private int quantidadeDoacoes;

    @ManyToOne
    @JoinColumn(name = "campanha")
    private Campanha campanha;

    public Relatorio() {

    }
}