package collectiva.org.collecta.repository;

import collectiva.org.collecta.domain.Doacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class DoacaoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Create
    @Transactional
    public void salvarDoacao(Doacao doacao) {
        entityManager.persist(doacao);
    }

    // Read
    @Transactional
    public Doacao buscarDoacaoPorId(UUID id) {
        return entityManager.find(Doacao.class, id);
    }

    @Transactional
    public List<Doacao> buscarTodasDoacoes() {
        String jpql = "SELECT c FROM Doacao c"; // JPQL para buscar todos os registros
        return entityManager.createQuery(jpql, Doacao.class).getResultList();

    }

    // Update
    @Transactional
    public Doacao atualizarDoacao(UUID id, Doacao doacao) {
        Doacao doacaoAntiga = entityManager.find(Doacao.class, id);
        doacaoAntiga.setValor(doacao.getValor());
        doacaoAntiga.setDataHora(doacao.getDataHora());
        doacaoAntiga.setModoContribuicao(doacao.getModoContribuicao());
        return doacaoAntiga;
    }

    // Delete
    @Transactional
    public void excluirDoacao(UUID id) {
        Doacao Doacao = entityManager.find(Doacao.class, id);
        entityManager.remove(Doacao);
    }
}
