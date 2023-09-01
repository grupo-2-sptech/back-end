package collectiva.org.collecta.repository;

import collectiva.org.collecta.domain.Doador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class DoadorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Create
    @Transactional
    public void salvarDoador(Doador doador) {
        entityManager.persist(doador);
    }

    // Read
    @Transactional
    public Doador buscarDoadorPorId(UUID id) {
        return entityManager.find(Doador.class, id);
    }

    @Transactional
    public List<Doador> buscarTodosDoadores() {
        String jpql = "SELECT c FROM Doador c"; // JPQL para buscar todos os registros
        return entityManager.createQuery(jpql, Doador.class).getResultList();

    }

    // Update
    @Transactional
    public Doador atualizarDoador(UUID id, Doador doador) {
        Doador doadorAntigo = entityManager.find(Doador.class, id);
        doadorAntigo.setEmail(doador.getEmail());
        doadorAntigo.setSenha(doador.getSenha());
        doadorAntigo.setTelefone(doador.getTelefone());
        doadorAntigo.setNome(doador.getNome());
        doadorAntigo.setSobrenome(doador.getSobrenome());
        doadorAntigo.setCpf(doador.getCpf());
        doadorAntigo.setDataNascimento(doador.getDataNascimento());
        return doadorAntigo;
    }

    // Delete
    @Transactional
    public void excluirDoador(UUID id) {
        Doador Doador = entityManager.find(Doador.class, id);
        entityManager.remove(Doador);
    }
}
