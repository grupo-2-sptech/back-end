package collectiva.org.collecta.domain;

import collectiva.org.collecta.domain.enums.CategoriaCampanha;
import collectiva.org.collecta.domain.enums.StatusCampanha;
import collectiva.org.collecta.domain.enums.TipoCampanha;
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

    @Enumerated(EnumType.STRING)
    private StatusCampanha statusCampanha;

    @Enumerated(EnumType.STRING)
    private TipoCampanha tipoCampanha;

    @Enumerated(EnumType.STRING)
    private CategoriaCampanha categoriaCampanha;

    private int visualizacoes;

    @ManyToOne
    @JoinColumn(name = "organizacao")
    private Organizacao organizacao;

    @OneToMany(mappedBy = "campanha")
    private List<FinanceiroCampanha> financeirosCampanha = new ArrayList<FinanceiroCampanha>();

    @OneToMany(mappedBy = "campanha")
    private List<Relatorio> relatorios = new ArrayList<>();

    @OneToMany(mappedBy = "campanha")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "campanha")
    private List<EventoCampanha> eventoCampanhas = new ArrayList<>();

}
