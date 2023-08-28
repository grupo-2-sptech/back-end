package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Conta {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    private String email;
    private String senha;
    private String telefone;
}

