package collectiva.org.collecta.repository;

import collectiva.org.collecta.domain.ComentarioOrganizacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ComentarioOrganizacaoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Create
    @Transactional
    public void salvarComentario(ComentarioOrganizacao comentarioOrganizacao) {
        entityManager.persist(comentarioOrganizacao);
    }

    // Read
    @Transactional
    public ComentarioOrganizacao buscarComentarioPorId(UUID id) {
        return entityManager.find(ComentarioOrganizacao.class, id);
    }

    @Transactional
    public List<ComentarioOrganizacao> buscarTodosComentarios() {
        String jpql = "SELECT c FROM ComentarioOrganizacao c"; // JPQL para buscar todos os registros
        return entityManager.createQuery(jpql, ComentarioOrganizacao.class).getResultList();

    }

    // Update
    @Transactional
    public ComentarioOrganizacao atualizarComentario(UUID id, ComentarioOrganizacao comentarioOrganizacao) {
        ComentarioOrganizacao comentarioAntigo = entityManager.find(ComentarioOrganizacao.class, id);
        comentarioAntigo.setComentario(comentarioOrganizacao.getComentario());
        comentarioAntigo.setData(comentarioOrganizacao.getData());
        return comentarioAntigo;
    }

    // Delete
    @Transactional
    public void excluirComentario(UUID id) {
        ComentarioOrganizacao comentarioOrganizacao = entityManager.find(ComentarioOrganizacao.class, id);
        entityManager.remove(comentarioOrganizacao);
    }
}
