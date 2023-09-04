package collectiva.org.collecta.domain;

import collectiva.org.collecta.domain.enums.TipoConta;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;


@Entity
@Data
@AllArgsConstructor
public class ComentarioDoador extends Comentario {
    @ManyToOne
    @JoinColumn(name = "doador")
    private Doador doador;

    public ComentarioDoador() {
        super();
    }

    @Builder
    public ComentarioDoador(UUID id, String comentario, Date data, TipoConta tipoConta, Post post, Doador doador) {
        super(id, comentario, data, tipoConta, post);
        this.doador = doador;
    }


}
