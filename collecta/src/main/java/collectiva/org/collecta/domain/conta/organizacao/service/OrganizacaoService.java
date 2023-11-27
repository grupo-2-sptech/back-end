package collectiva.org.collecta.domain.conta.organizacao.service;

import collectiva.org.collecta.domain.conta.organizacao.Organizacao;
import collectiva.org.collecta.domain.conta.organizacao.repository.OrganizacaoRepository;
import collectiva.org.collecta.domain.conta.usuario.service.ContaService;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrganizacaoService {
    private final OrganizacaoRepository organizacaoRepository;
    private final ContaService contaService;
    private final PasswordEncoder passwordEncoder;

    public Organizacao salvarOrganizacao(Organizacao organizacao) {
        contaService.buscarPorEmail(organizacao.getEmail());
        String senhaCriptografada = passwordEncoder.encode(organizacao.getSenha());
        organizacao.setSenha(senhaCriptografada);
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
        Organizacao organizacaoNova = buscarOrganizacaoPorId(id);
        organizacaoNova.setTelefone(organizacao.getTelefone());
        organizacaoNova.setNomeFantasia(organizacao.getNomeFantasia());
        organizacaoNova.setNomeSocial(organizacao.getNomeSocial());
        organizacaoNova.setDataFundacao(organizacao.getDataFundacao());
        organizacaoNova.setCnpj(organizacao.getCnpj());
        return organizacaoRepository.save(organizacaoNova);
    }

    public void deletarOrganizacao(UUID id) {
        if (!organizacaoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Organizacao");
        }
        organizacaoRepository.deleteById(id);
    }
}

