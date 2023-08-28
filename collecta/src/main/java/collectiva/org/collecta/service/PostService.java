package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Post;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    EntityManager entityManager;
    public void salvarPost(Post post){
        entityManager.persist(post);
    }
}
