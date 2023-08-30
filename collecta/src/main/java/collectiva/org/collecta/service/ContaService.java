package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Conta;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
    @Autowired
    private EntityManager entityManager;

    public void salvarConta(Conta conta){
        entityManager.persist(conta);
    }
}
