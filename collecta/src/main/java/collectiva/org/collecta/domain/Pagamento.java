package collectiva.org.collecta.domain;

import collectiva.org.collecta.domain.enums.FormaPagamento;
import collectiva.org.collecta.domain.enums.Plano;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private BigDecimal valor;
    private LocalDateTime dataHora;
    private int parcelas;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @Enumerated(EnumType.STRING)
    private Plano plano;
}
