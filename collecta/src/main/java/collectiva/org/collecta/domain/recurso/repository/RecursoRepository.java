package collectiva.org.collecta.domain.recurso.repository;

import collectiva.org.collecta.domain.recurso.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, UUID> {
}
