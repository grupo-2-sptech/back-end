package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Organizacao;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizacaoService {
    @Autowired
    EntityManager entityManager;

    public void salvarOrganizacao(Organizacao organizacao){
        entityManager.persist(organizacao);
    }
}
