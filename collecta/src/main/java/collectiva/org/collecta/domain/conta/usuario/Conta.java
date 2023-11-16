package collectiva.org.collecta.domain.conta.usuario;

import collectiva.org.collecta.enums.TipoConta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Conta {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String email;
    private String senha;
    private String telefone;
    private TipoConta tipoConta;

}

