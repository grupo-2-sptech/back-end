package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Organizacao;
import collectiva.org.collecta.repository.OrganizacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrganizacaoService {
    private final OrganizacaoRepository organizacaoRepository;

    public ResponseEntity<Organizacao> salvarOrganizacao(Organizacao organizacao) {
        organizacaoRepository.save(organizacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(organizacao);
    }

    public ResponseEntity<List<Organizacao>> buscarTodasOrganizacoes() {
        List<Organizacao> organizacao = organizacaoRepository.findAll();
        if (organizacao.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(organizacao);
    }

    public ResponseEntity<Optional<Organizacao>> buscarOrganizacaoPorId(UUID id) {
        Optional<Organizacao> organizacao = organizacaoRepository.findById(id);
        if (organizacao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(organizacao);
    }

    public ResponseEntity<Organizacao> atualizarOrganizacao(UUID id, Organizacao organizacao) {
        Optional<Organizacao> organizacaoAntiga = organizacaoRepository.findById(id);
        Organizacao organizacaoExistente = organizacaoAntiga.get();

        Organizacao organizacaoAtualizada = Organizacao.builder()
                .id(organizacaoExistente.getId())
                .email(organizacao.getEmail())
                .senha(organizacao.getSenha())
                .telefone(organizacao.getTelefone())
                .nomeFantasia(organizacao.getNomeFantasia())
                .nomeSocial(organizacao.getNomeSocial())
                .cnpj(organizacao.getCnpj())
                .dataFundacao(organizacao.getDataFundacao())
                .build();

        organizacaoRepository.save(organizacaoAtualizada);

        return ResponseEntity.ok().body(organizacaoAtualizada);
    }

    public ResponseEntity<Void> deletarOrganizacao(UUID id) {
        if (organizacaoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        organizacaoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

