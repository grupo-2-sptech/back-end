package collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.repository;

import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.ContribuicaoMonetaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface ContribuicaoMonetariaRepository extends JpaRepository<ContribuicaoMonetaria, UUID> {
    int countContribuicaoMonetariaByFinanceiroCampanha_Campanha_IdAndDataHoraBetween(UUID id, LocalDateTime startDate, LocalDateTime endDate);
}
