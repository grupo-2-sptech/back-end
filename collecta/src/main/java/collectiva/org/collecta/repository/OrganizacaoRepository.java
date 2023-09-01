package collectiva.org.collecta.repository;

import collectiva.org.collecta.domain.Organizacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class OrganizacaoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Create
    @Transactional
    public void salvarOrganizacao(Organizacao organizacao) {
        entityManager.persist(organizacao);
    }

    // Read
    @Transactional
    public Organizacao buscarOrganizacaoPorId(UUID id) {
        return entityManager.find(Organizacao.class, id);
    }

    @Transactional
    public List<Organizacao> buscarTodasOrganizacoes() {
        String jpql = "SELECT c FROM Organizacao c"; // JPQL para buscar todos os registros
        return entityManager.createQuery(jpql, Organizacao.class).getResultList();

    }

    // Update
    @Transactional
    public Organizacao atualizarOrganizacao(UUID id, Organizacao organizacao) {
        Organizacao organizacaoAntiga = entityManager.find(Organizacao.class, id);
        organizacaoAntiga.setEmail(organizacao.getEmail());
        organizacaoAntiga.setSenha(organizacao.getSenha());
        organizacaoAntiga.setTelefone(organizacao.getTelefone());
        organizacaoAntiga.setNomeFantasia(organizacao.getNomeFantasia());
        organizacaoAntiga.setNomeSocial(organizacao.getNomeSocial());
        organizacaoAntiga.setCnpj(organizacao.getCnpj());
        organizacaoAntiga.setDataFundacao(organizacao.getDataFundacao());
        return organizacaoAntiga;
    }

    // Delete
    @Transactional
    public void excluirOrganizacao(UUID id) {
        Organizacao Organizacao = entityManager.find(Organizacao.class, id);
        entityManager.remove(Organizacao);
    }
}
