package collectiva.org.collecta.domain.conta.doador.repository;

import collectiva.org.collecta.domain.conta.doador.Doador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoadorRepository extends JpaRepository<Doador, UUID> {
    Optional<Doador> findByEmail(String email);
}
