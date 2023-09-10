package collectiva.org.collecta.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
public class Organizacao extends Conta {
    private String nomeSocial;
    private String nomeFantasia;
    private String cnpj;
    private LocalDateTime dataFundacao;

    @OneToMany(mappedBy = "organizacao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Campanha> campanha = new ArrayList<>();

    @OneToMany(mappedBy = "organizacao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ComentarioOrganizacao> comentarios = new ArrayList<>();

    @Builder
    public Organizacao(UUID id, String email, String senha, String telefone, String nomeSocial, String nomeFantasia, String cnpj, LocalDateTime dataFundacao, List<Campanha> campanha, List<ComentarioOrganizacao> comentarios) {
        super(id, email, senha, telefone);
        this.nomeSocial = nomeSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.campanha = campanha;
        this.comentarios = comentarios;
    }

    public Organizacao() {

    }
}
