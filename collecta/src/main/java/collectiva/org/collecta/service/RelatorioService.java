package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Relatorio;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelatorioService {
    @Autowired
    EntityManager entityManager;
    public void salvarRelatorio(Relatorio relatorio){
        entityManager.persist(relatorio);
    }
}
