package collectiva.org.collecta.domain.conta.Doador.repository;

import collectiva.org.collecta.domain.conta.Doador.Doador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoadorRepository extends JpaRepository<Doador, UUID> {
    Optional<Doador> findByEmail(String email);
    boolean existsByEmail(String email);
}
