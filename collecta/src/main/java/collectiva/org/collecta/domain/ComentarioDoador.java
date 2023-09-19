package collectiva.org.collecta.domain;

import collectiva.org.collecta.domain.enums.TipoConta;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDoador extends Comentario {
    @ManyToOne
    @JoinColumn(name = "doador")
    private Doador doador;

    @Builder
    public ComentarioDoador(UUID id, String comentario, LocalDateTime data, TipoConta tipoConta, Post post, Doador doador) {
        super(id, comentario, data, tipoConta, post);
        this.doador = doador;
    }


}
