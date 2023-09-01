package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
    @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public abstract class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    private String email;
    private String senha;
    private String telefone;

}

