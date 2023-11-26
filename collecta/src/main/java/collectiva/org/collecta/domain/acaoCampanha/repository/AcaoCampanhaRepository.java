package collectiva.org.collecta.domain.acaoCampanha.repository;

import collectiva.org.collecta.domain.acaoCampanha.AcaoCampanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AcaoCampanhaRepository extends JpaRepository<AcaoCampanha, UUID> {
}
