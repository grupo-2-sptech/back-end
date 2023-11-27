package collectiva.org.collecta.domain.comentario.comentarioDoador.repository;

import collectiva.org.collecta.domain.comentario.comentarioDoador.ComentarioDoador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ComentarioDoadorRepository extends JpaRepository<ComentarioDoador, UUID> {
}
