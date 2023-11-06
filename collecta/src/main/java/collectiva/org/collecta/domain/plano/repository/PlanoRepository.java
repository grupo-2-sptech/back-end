package collectiva.org.collecta.domain.plano.repository;

import collectiva.org.collecta.domain.plano.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, UUID> {
}
