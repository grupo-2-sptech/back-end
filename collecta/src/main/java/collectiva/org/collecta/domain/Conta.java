package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@AllArgsConstructor
public abstract class Conta {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String email;
    private String senha;
    private String telefone;

    public Conta() {
    }
}

