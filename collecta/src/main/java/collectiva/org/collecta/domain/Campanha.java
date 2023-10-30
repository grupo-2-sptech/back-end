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
public class Campanha {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String nome;
    private String descricao;

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String status;

    @ManyToOne
    @JoinColumn(name = "organizacao")
    private Organizacao organizacao;

    @ManyToOne
    @JoinColumn(name = "categoria")
    private Categoria categoria;

    @OneToMany(mappedBy = "campanha")
    private List<FinanceiroCampanha> financeirosCampanha = new ArrayList<FinanceiroCampanha>();

    @OneToMany(mappedBy = "campanha")
    private List<Relatorio> relatorios = new ArrayList<>();

    @OneToMany(mappedBy = "campanha")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "campanha")
    private List<EventoCampanha> eventoCampanhas = new ArrayList<>();

}
