package collectiva.org.collecta.domain;

import collectiva.org.collecta.domain.enums.TipoConta;
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
public class ComentarioOrganizacao extends Comentario {
    @ManyToOne
    @JoinColumn(name = "organizacao")
    private Organizacao organizacao;

    @Builder
    public ComentarioOrganizacao(UUID id, String comentario, LocalDateTime data, TipoConta tipoConta, Post post, Organizacao organizacao) {
        super(id, comentario, data, tipoConta, post);
        this.organizacao = organizacao;
    }

}
