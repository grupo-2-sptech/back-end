package collectiva.org.collecta.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ComentarioOrganizacao extends Comentario{
    @ManyToOne
    @JoinColumn(name="organizacao")
    private Organizacao organizacao;
}
