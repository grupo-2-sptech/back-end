package collectiva.org.collecta.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Organizacao extends Conta {
    private String nomeSocial;
    private String nomeFantasia;
    private String cnpj;
    private Date dataFundacao;

    @OneToMany(mappedBy = "organizacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Campanha> campanha = new ArrayList<>();

    @OneToMany(mappedBy = "organizacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComentarioOrganizacao> comentarios = new ArrayList<>();
}
