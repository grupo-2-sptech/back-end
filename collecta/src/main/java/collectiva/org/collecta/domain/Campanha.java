package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Campanha {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String nome;
    private String descricao;
    private BigDecimal valorMeta;
    private BigDecimal valorArrecadado;
    private Date dataInicio;
    private Date dataFim;
    private String status;

    @ManyToOne
    @JoinColumn(name = "organizacao")
    private Organizacao organizacao;

    @OneToMany(mappedBy = "campanha", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Doacao> doacoes = new ArrayList<Doacao>();

    @OneToMany(mappedBy = "campanha", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Relatorio> relatorios = new ArrayList<>();

    @OneToMany(mappedBy = "campanha", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

}
