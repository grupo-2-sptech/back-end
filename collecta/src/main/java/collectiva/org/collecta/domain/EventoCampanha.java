package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Builder
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
}
