package collectiva.org.collecta.domain.acaoCampanha;

import collectiva.org.collecta.domain.relatorio.Relatorio;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AcaoCampanha {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;
    private String nome;

    @Column(columnDefinition = "VARCHAR(700)")
    private String descricao;

    private LocalDate data;
    private BigDecimal valor;

    @ManyToOne
    private Relatorio relatorio;

}