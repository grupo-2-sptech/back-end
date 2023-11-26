package collectiva.org.collecta.domain.campanha.repository;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.enums.CategoriaCampanha;
import collectiva.org.collecta.enums.TipoCampanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, UUID> {
    List<Campanha> findTop3ByTipoCampanhaOrderByVisualizacoesDesc(TipoCampanha tipoCampanha);
    List<Campanha> findByCategoriaCampanha(CategoriaCampanha categoriaCampanha);
}
