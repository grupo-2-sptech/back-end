package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Builder
@Data
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
    @JoinColumn(name = "campanha")
    private Campanha campanha;

}
