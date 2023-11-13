package collectiva.org.collecta.domain.conta.Organizacao.service;

import collectiva.org.collecta.domain.conta.Organizacao.Organizacao;
import collectiva.org.collecta.domain.conta.Organizacao.repository.OrganizacaoRepository;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrganizacaoService {
    private final OrganizacaoRepository organizacaoRepository;

    public Organizacao salvarOrganizacao(Organizacao organizacao) {
        return organizacaoRepository.save(organizacao);
    }

    public List<Organizacao> buscarTodasOrganizacoes() {
        return organizacaoRepository.findAll();
    }

    public Organizacao buscarOrganizacaoPorId(UUID id) {
        return organizacaoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Organizacao"));
    }

    public Organizacao atualizarOrganizacao(UUID id, Organizacao organizacao) {
        buscarOrganizacaoPorId(id);
        organizacao.setId(id);
        return organizacaoRepository.save(organizacao);
    }

    public void deletarOrganizacao(UUID id) {
        if (!organizacaoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Organizacao");
        }
        organizacaoRepository.deleteById(id);
    }
}

