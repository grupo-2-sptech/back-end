package collectiva.org.collecta.repository;

import collectiva.org.collecta.domain.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PostRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Create
    @Transactional
    public void salvarPost(Post post) {
        entityManager.persist(post);
    }

    // Read
    @Transactional
    public Post buscarPostPorId(UUID id) {
        return entityManager.find(Post.class, id);
    }

    @Transactional
    public List<Post> buscarTodosPosts() {
        String jpql = "SELECT c FROM Post c"; // JPQL para buscar todos os registros
        return entityManager.createQuery(jpql, Post.class).getResultList();

    }

    // Update
    @Transactional
    public Post atualizarPost(UUID id, Post post) {
        Post postAntigo = entityManager.find(Post.class, id);
        postAntigo.setData(post.getData());
        postAntigo.setTitulo(post.getTitulo());
        postAntigo.setConteudo(post.getConteudo());
        postAntigo.setData(post.getData());
        return postAntigo;
    }

    // Delete
    @Transactional
    public void excluirPost(UUID id) {
        Post Post = entityManager.find(Post.class, id);
        entityManager.remove(Post);
    }
}
