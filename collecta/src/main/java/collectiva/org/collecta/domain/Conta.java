package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
    @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public abstract class Conta {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String email;
    private String senha;
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "comentario_autor_id")
    private ComentarioAutor comentarioAutor;

}

