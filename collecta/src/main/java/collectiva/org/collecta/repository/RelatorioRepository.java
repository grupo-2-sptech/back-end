package collectiva.org.collecta.repository;

import collectiva.org.collecta.domain.Relatorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class RelatorioRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Create
    @Transactional
    public void salvarRelatorio(Relatorio relatorio) {
        entityManager.persist(relatorio);
    }

    // Read
    @Transactional
    public Relatorio buscarRelatorioPorId(UUID id) {
        return entityManager.find(Relatorio.class, id);
    }

    @Transactional
    public List<Relatorio> buscarTodosRelatorios() {
        String jpql = "SELECT c FROM Relatorio c"; // JPQL para buscar todos os registros
        return entityManager.createQuery(jpql, Relatorio.class).getResultList();

    }

    // Update
    @Transactional
    public Relatorio atualizarRelatorio(UUID id, Relatorio relatorio) {
        Relatorio relatorioAntigo = entityManager.find(Relatorio.class, id);
        relatorioAntigo.setData(relatorio.getData());
        relatorioAntigo.setValorArrecadado(relatorio.getValorArrecadado());
        relatorioAntigo.setQuantidadeDoacoes(relatorio.getQuantidadeDoacoes());
        return relatorioAntigo;
    }

    // Delete
    @Transactional
    public void excluirRelatorio(UUID id) {
        Relatorio Relatorio = entityManager.find(Relatorio.class, id);
        entityManager.remove(Relatorio);
    }
}
