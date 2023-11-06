package collectiva.org.collecta.domain.endereco;

import collectiva.org.collecta.domain.eventoCampanha.EventoCampanha;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    private String logradouro;
    private String cidade;
    private String estado;
    private String cep;
    private int numero;

    @ManyToOne
    @JoinColumn(name = "eventoCampanha")
    private EventoCampanha eventoCampanha;

}
