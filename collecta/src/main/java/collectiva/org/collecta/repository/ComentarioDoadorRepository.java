package collectiva.org.collecta.repository;

import collectiva.org.collecta.domain.ComentarioDoador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ComentarioDoadorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Create
    @Transactional
    public void salvarComentario(ComentarioDoador comentario) {
        entityManager.persist(comentario);
    }

    // Read
    @Transactional
    public ComentarioDoador buscarComentarioPorId(UUID id) {
        return entityManager.find(ComentarioDoador.class, id);
    }

    @Transactional
    public List<ComentarioDoador> buscarTodosComentarios() {
        String jpql = "SELECT c FROM ComentarioDoador c"; // JPQL para buscar todos os registros
        return entityManager.createQuery(jpql, ComentarioDoador.class).getResultList();

    }

    // Update
    @Transactional
    public ComentarioDoador atualizarComentario(UUID id, ComentarioDoador comentarioDoador) {
        ComentarioDoador comentarioAntigo = entityManager.find(ComentarioDoador.class, id);
        comentarioAntigo.setComentario(comentarioDoador.getComentario());
        comentarioAntigo.setData(comentarioDoador.getData());
        return comentarioAntigo;
    }

    // Delete
    @Transactional
    public void excluirComentario(UUID id) {
        ComentarioDoador comentario = entityManager.find(ComentarioDoador.class, id);
        entityManager.remove(comentario);
    }
}
