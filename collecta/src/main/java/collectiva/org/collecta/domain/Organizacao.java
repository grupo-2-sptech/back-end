package collectiva.org.collecta.domain;

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
    private List<Campanha> campanha = new ArrayList<>();

    @OneToMany(mappedBy = "organizacao")
    private List<ComentarioOrganizacao> comentarios = new ArrayList<>();

    @Builder
    public Organizacao(UUID id, String email, String senha, String telefone, String nomeSocial, String nomeFantasia, String cnpj, LocalDateTime dataFundacao) {
        super(id, email, senha, telefone);
        this.nomeSocial = nomeSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
    }
}
