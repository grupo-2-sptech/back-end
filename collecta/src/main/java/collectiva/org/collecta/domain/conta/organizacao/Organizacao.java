package collectiva.org.collecta.domain.conta.organizacao;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.ComentarioOrganizacao;
import collectiva.org.collecta.domain.conta.usuario.Conta;
import collectiva.org.collecta.enums.TipoConta;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Organizacao extends Conta {
    private String nomeSocial;
    private String nomeFantasia;
    private String cnpj;
    private LocalDateTime dataFundacao;

    @OneToMany(mappedBy = "organizacao")
    private List<Campanha> campanhas = new ArrayList<>();

    @OneToMany(mappedBy = "organizacao")
    private List<ComentarioOrganizacao> comentarios = new ArrayList<>();

    @Builder

    public Organizacao(UUID id, String email, String senha, String telefone, TipoConta tipoConta, String nomeSocial, String nomeFantasia, String cnpj, LocalDateTime dataFundacao, List<Campanha> campanhas, List<ComentarioOrganizacao> comentarios) {
        super(id, email, senha, telefone, tipoConta);
        this.nomeSocial = nomeSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.campanhas = campanhas;
        this.comentarios = comentarios;
    }
}
