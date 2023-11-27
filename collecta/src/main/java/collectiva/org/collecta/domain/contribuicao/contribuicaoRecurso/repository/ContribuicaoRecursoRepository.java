package collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.repository;

import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.ContribuicaoRecurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContribuicaoRecursoRepository extends JpaRepository<ContribuicaoRecurso, UUID> {
}