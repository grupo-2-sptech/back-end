package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Comentario;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {
    @Autowired
    EntityManager entityManager;
    public void salvarComentario(Comentario comentario){
        entityManager.persist(comentario);
    }
}
