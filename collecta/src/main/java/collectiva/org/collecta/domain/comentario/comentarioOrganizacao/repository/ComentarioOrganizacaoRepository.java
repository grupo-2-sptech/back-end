package collectiva.org.collecta.domain.comentario.comentarioOrganizacao.repository;

import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.ComentarioOrganizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ComentarioOrganizacaoRepository extends JpaRepository<ComentarioOrganizacao, UUID> {
}
