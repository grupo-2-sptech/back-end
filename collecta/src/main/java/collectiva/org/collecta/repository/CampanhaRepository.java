package collectiva.org.collecta.repository;

import collectiva.org.collecta.domain.Campanha;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CampanhaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Create
    @Transactional
    public void salvarCampanha(Campanha campanha) {
        entityManager.persist(campanha);
    }

    // Read
    @Transactional
    public Campanha buscarCampanhaPorId(UUID id) {
        return entityManager.find(Campanha.class, id);
    }

    @Transactional
    public List<Campanha> buscarTodasCampanhas() {
        String jpql = "SELECT c FROM Campanha c"; // JPQL para buscar todos os registros
        return entityManager.createQuery(jpql, Campanha.class).getResultList();

    }

    // Update
    @Transactional
    public Campanha atualizarCampanha(UUID id, Campanha campanha) {
        Campanha campanhaAntiga = entityManager.find(Campanha.class, id);
        campanhaAntiga.setNome(campanha.getNome());
        campanhaAntiga.setDescricao(campanha.getDescricao());
        campanhaAntiga.setValorMeta(campanha.getValorMeta());
        campanhaAntiga.setValorArrecadado(campanha.getValorArrecadado());
        campanhaAntiga.setDataInicio(campanha.getDataInicio());
        campanhaAntiga.setDataFim(campanha.getDataFim());
        campanhaAntiga.setStatus(campanha.getStatus());
        return campanhaAntiga;
    }

    // Delete
    @Transactional
    public void excluirCampanha(UUID id) {
        Campanha Campanha = entityManager.find(Campanha.class, id);
        entityManager.remove(Campanha);
    }
}
