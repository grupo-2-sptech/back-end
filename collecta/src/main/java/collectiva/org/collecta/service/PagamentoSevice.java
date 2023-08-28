package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Pagamento;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoSevice {
    @Autowired
    EntityManager entityManager;

    public void salvarPagamento(Pagamento pagamento){
        entityManager.persist(pagamento);
    }
}
