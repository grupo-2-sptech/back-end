package collectiva.org.collecta.domain;

import collectiva.org.collecta.domain.enums.StatusPlano;
import collectiva.org.collecta.domain.enums.TipoPlano;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Plano {
    @Id
    @GeneratedValue(generator = "uuid1")
    private UUID id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    @Enumerated(EnumType.STRING)
    private TipoPlano tipoPlano;

    @Enumerated(EnumType.STRING)
    private StatusPlano statusPlano;

    @ManyToOne
    @JoinColumn(name = "doador")
    private Doador doador;

}
