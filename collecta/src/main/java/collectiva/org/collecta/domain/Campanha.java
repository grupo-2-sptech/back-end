package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Campanha {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String nome;
    private String descricao;
    private BigDecimal valorMeta;
    private BigDecimal valorArrecadado;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String status;

    @ManyToOne
    @JoinColumn(name = "organizacao")
    private Organizacao organizacao;

    @OneToMany(mappedBy = "campanha", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Doacao> doacoes = new ArrayList<Doacao>();

    @OneToMany(mappedBy = "campanha", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Relatorio> relatorios = new ArrayList<>();

    @OneToMany(mappedBy = "campanha", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "campanha")
    private List<Endereco> enderecos = new ArrayList<>();

}
