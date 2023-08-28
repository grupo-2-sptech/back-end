package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Doador extends Conta {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private String cpf;
}
