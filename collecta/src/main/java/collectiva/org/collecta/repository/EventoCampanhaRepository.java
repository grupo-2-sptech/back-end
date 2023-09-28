package collectiva.org.collecta.repository;

import collectiva.org.collecta.domain.EventoCampanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventoCampanhaRepository extends JpaRepository<EventoCampanha, UUID> {
}
