package collectiva.org.collecta.domain.comentario.comentarioOrganizacao;

import collectiva.org.collecta.domain.comentario.Comentario;
import collectiva.org.collecta.domain.conta.organizacao.Organizacao;
import collectiva.org.collecta.domain.postCampanha.Post;
import collectiva.org.collecta.enums.TipoConta;
import jakarta.persistence.Entity;
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
    private Organizacao organizacao;

    @Builder
    public ComentarioOrganizacao(UUID id, String comentario, LocalDateTime data, TipoConta tipoConta, Post post, Organizacao organizacao) {
        super(id, comentario, data, tipoConta, post);
        this.organizacao = organizacao;
    }

}
