package collectiva.org.collecta.domain.campanha;

import collectiva.org.collecta.domain.conta.organizacao.Organizacao;
import collectiva.org.collecta.domain.eventoCampanha.EventoCampanha;
import collectiva.org.collecta.domain.financeiroCampanha.FinanceiroCampanha;
import collectiva.org.collecta.domain.postCampanha.Post;
import collectiva.org.collecta.domain.recurso.Recurso;
import collectiva.org.collecta.domain.relatorio.Relatorio;
import collectiva.org.collecta.enums.CategoriaCampanha;
import collectiva.org.collecta.enums.StatusCampanha;
import collectiva.org.collecta.enums.TipoCampanha;
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
    private String urlImagem;


    @ManyToOne
    private Organizacao organizacao;

    @OneToOne(mappedBy = "campanha")
    private FinanceiroCampanha financeirosCampanha;

    @OneToMany(mappedBy = "campanha")
    private List<Relatorio> relatorios = new ArrayList<>();

    @OneToMany(mappedBy = "campanha")
    private List<Recurso> recursos = new ArrayList<>();

    @OneToMany(mappedBy = "campanha")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "campanha")
    private List<EventoCampanha> eventoCampanhas = new ArrayList<>();

}
