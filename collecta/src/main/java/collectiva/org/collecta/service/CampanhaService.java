package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Campanha;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampanhaService {
    @Autowired
    EntityManager entityManager;

    public void salvarCompanha(Campanha campanha){
        entityManager.persist(campanha);

    }
}
