package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventoCampanha {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    private String nome;
    private String descricao;
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "campanha")
    private Campanha campanha;

    @OneToMany(mappedBy = "eventoCampanha")
    private List<Endereco> enderecos = new ArrayList<>();

    @OneToMany(mappedBy = "eventoCampanha")
    private List<ContribuicaoServico> contribuicaoServicos = new ArrayList<>();
}
