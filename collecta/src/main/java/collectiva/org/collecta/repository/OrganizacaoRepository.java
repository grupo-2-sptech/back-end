package collectiva.org.collecta.repository;

import collectiva.org.collecta.domain.Campanha;
import collectiva.org.collecta.domain.Organizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrganizacaoRepository extends JpaRepository<Organizacao, UUID> {
}
