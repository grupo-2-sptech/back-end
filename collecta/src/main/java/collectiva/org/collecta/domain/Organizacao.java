package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Organizacao extends Conta {
    private String nomeSocial;
    private String nomeFantasia;
    private String cnpj;
    private Date dataFundacao;

    @OneToMany(mappedBy = "organizacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Campanha> campanha = new ArrayList<>();
}
