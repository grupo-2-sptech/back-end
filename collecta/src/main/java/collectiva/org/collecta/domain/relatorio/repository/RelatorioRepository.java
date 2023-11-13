package collectiva.org.collecta.domain.relatorio.repository;

import collectiva.org.collecta.domain.relatorio.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, UUID> {
}
