package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Doacao;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoacaoService {
    @Autowired
    EntityManager entityManager;
    public void salvarDoacao(Doacao doacao){
        entityManager.persist(doacao);
    }
}
