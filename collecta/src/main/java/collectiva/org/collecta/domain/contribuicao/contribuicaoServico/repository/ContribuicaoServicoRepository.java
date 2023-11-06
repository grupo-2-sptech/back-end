package collectiva.org.collecta.domain.contribuicao.contribuicaoServico.repository;

import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.ContribuicaoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContribuicaoServicoRepository extends JpaRepository<ContribuicaoServico, UUID> {
}
