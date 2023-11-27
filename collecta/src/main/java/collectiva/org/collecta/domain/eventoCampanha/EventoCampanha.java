package collectiva.org.collecta.domain.eventoCampanha;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.ContribuicaoServico;
import collectiva.org.collecta.domain.endereco.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Integer qtdVaga;
    private String urlImagem;
    private String local;
    private String descricao;
    private LocalDateTime dataHora;

    @JsonIgnore
    @ManyToOne
    private Campanha campanha;
    @JsonIgnore
    @OneToMany(mappedBy = "eventoCampanha")
    private List<Endereco> enderecos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "eventoCampanha")
    private List<ContribuicaoServico> contribuicaoServicos = new ArrayList<>();
}
