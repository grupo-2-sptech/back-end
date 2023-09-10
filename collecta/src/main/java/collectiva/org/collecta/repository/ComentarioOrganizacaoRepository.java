package collectiva.org.collecta.repository;

import collectiva.org.collecta.domain.ComentarioOrganizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ComentarioOrganizacaoRepository extends JpaRepository<ComentarioOrganizacao, UUID> {
}
