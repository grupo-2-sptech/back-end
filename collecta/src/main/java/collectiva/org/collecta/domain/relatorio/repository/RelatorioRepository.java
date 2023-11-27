package collectiva.org.collecta.domain.relatorio.repository;

import collectiva.org.collecta.domain.relatorio.Relatorio;
import collectiva.org.collecta.domain.relatorio.dto.GeneratorRelatorioDTO;
import collectiva.org.collecta.enums.StatusContribuicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, UUID> {
    @Query("SELECT NEW collectiva.org.collecta.domain.relatorio.dto.GeneratorRelatorioDTO(fc.valorMeta, fc.valorAtingido, c.visualizacoes, count(cm.id), count(cr.id), count(cs.id)) " +
            "FROM Campanha c " +
            "JOIN FinanceiroCampanha fc ON c.id = fc.campanha.id " +
            "LEFT JOIN ContribuicaoMonetaria cm ON fc.id = cm.financeiroCampanha.id " +
            "LEFT JOIN Recurso r ON c.id = r.campanha.id " +
            "LEFT JOIN ContribuicaoRecurso cr ON r.id = cr.recurso.id " +
            "LEFT JOIN EventoCampanha ec ON c.id = ec.campanha.id " +
            "LEFT JOIN ContribuicaoServico cs ON ec.id = cs.eventoCampanha.id " +
            "WHERE c.id = :campanhaId " +
            "AND (cm.statusContribuicao = :statusContribuicao)"+
            "AND (cm.dataHora BETWEEN :inicio AND :fim OR cm.dataHora IS NULL)" +
            "AND (cr.dataHora BETWEEN :inicio AND :fim OR cr.dataHora IS NULL) " +
            "AND (cs.dataHora BETWEEN :inicio AND :fim OR cs.dataHora IS NULL) ")
    GeneratorRelatorioDTO gerarRelatorioPorCampanha(
            @Param("campanhaId") UUID campanhaId,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim,
            @Param("statusContribuicao") StatusContribuicao statusContribuicao);
}
