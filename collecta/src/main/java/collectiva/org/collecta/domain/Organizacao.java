package collectiva.org.collecta.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Organizacao extends Conta{
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    private String nomeSocial;
    private String nomeFantasia;
    private String cnpj;
    private Date dataFundacao;
}
