package collectiva.org.collecta.domain.conta.usuario.repository;

import collectiva.org.collecta.domain.conta.usuario.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContaRepository extends JpaRepository<Conta, UUID> {
    Optional<Conta> findByEmail(String email);

    boolean existsByEmail(String email);
}
