package collectiva.org.collecta.domain.conta.Organizacao.repository;

import collectiva.org.collecta.domain.conta.Organizacao.Organizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrganizacaoRepository extends JpaRepository<Organizacao, UUID> {
}
