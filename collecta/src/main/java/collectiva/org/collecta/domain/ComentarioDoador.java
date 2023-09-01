package collectiva.org.collecta.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Entity
@Data
public class ComentarioDoador extends Comentario {
    @ManyToOne
    @JoinColumn(name="doador")
    private Doador doador;
}
