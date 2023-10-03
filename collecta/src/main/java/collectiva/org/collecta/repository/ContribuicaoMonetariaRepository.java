package collectiva.org.collecta.repository;

import collectiva.org.collecta.domain.ContribuicaoMonetaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContribuicaoMonetariaRepository extends JpaRepository<ContribuicaoMonetaria, UUID> {
}
