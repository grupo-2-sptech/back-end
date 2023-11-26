package collectiva.org.collecta.domain.financeiroCampanha.repository;

import collectiva.org.collecta.domain.financeiroCampanha.FinanceiroCampanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface FinanceiroCampanhaRepository extends JpaRepository<FinanceiroCampanha, UUID> {
    @Query("SELECT fc.valorMeta FROM FinanceiroCampanha fc WHERE fc.campanha.id = :id")
    BigDecimal findValorMetaById(@Param("id") UUID id);

    @Query("SELECT fc.valorAtingido FROM FinanceiroCampanha fc WHERE fc.campanha.id = :id")
    BigDecimal findValorAtingidoById(@Param("id") UUID id);
}
