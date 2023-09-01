package collectiva.org.collecta.repository;

import collectiva.org.collecta.domain.Pagamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PagamentoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Create
    @Transactional
    public void salvarPagamento(Pagamento pagamento) {
        entityManager.persist(pagamento);
    }

    // Read
    @Transactional
    public Pagamento buscarPagamentoPorId(UUID id) {
        return entityManager.find(Pagamento.class, id);
    }

    @Transactional
    public List<Pagamento> buscarTodosPagamentos() {
        String jpql = "SELECT c FROM Pagamento c"; // JPQL para buscar todos os registros
        return entityManager.createQuery(jpql, Pagamento.class).getResultList();

    }

    // Update
    @Transactional
    public Pagamento atualizarPagamento(UUID id, Pagamento pagamento) {
        Pagamento pagamentoAntigo = entityManager.find(Pagamento.class, id);
        pagamentoAntigo.setParcelas(pagamento.getParcelas());
        pagamentoAntigo.setFormaPagamento(pagamento.getFormaPagamento());
        return pagamentoAntigo;
    }

    // Delete
    @Transactional
    public void excluirPagamento(UUID id) {
        Pagamento Pagamento = entityManager.find(Pagamento.class, id);
        entityManager.remove(Pagamento);
    }
}
