package collectiva.org.collecta.domain.comentario.comentarioDoador;

import collectiva.org.collecta.domain.comentario.Comentario;
import collectiva.org.collecta.domain.conta.Doador.Doador;
import collectiva.org.collecta.domain.postCampanha.Post;
import collectiva.org.collecta.enums.TipoConta;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDoador extends Comentario {
    @ManyToOne
    private Doador doador;

    @Builder
    public ComentarioDoador(UUID id, String comentario, LocalDateTime data, TipoConta tipoConta, Post post, Doador doador) {
        super(id, comentario, data, tipoConta, post);
        this.doador = doador;
    }


}
