package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Doador;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoadorService {
    @Autowired
    private EntityManager entityManager;
    public void salvarDoador(Doador doador){
        entityManager.persist(doador);
    }
}
