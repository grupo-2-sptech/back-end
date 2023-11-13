package collectiva.org.collecta.domain.plano;

import collectiva.org.collecta.domain.conta.Doador.Doador;
import collectiva.org.collecta.enums.StatusPlano;
import collectiva.org.collecta.enums.TipoPlano;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
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

    @OneToOne
    private Doador doador;

}
