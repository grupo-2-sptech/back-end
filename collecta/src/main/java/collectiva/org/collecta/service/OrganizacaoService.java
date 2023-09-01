package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Organizacao;
import collectiva.org.collecta.repository.OrganizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrganizacaoService {
    @Autowired
    OrganizacaoRepository organizacaoRepository;

    public ResponseEntity<Organizacao> salvarOrganizacao(Organizacao organizacao) {
        organizacaoRepository.salvarOrganizacao(organizacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(organizacao);
    }

    public ResponseEntity<List<Organizacao>> buscarTodasOrganizacoes() {
        List<Organizacao> organizacao = organizacaoRepository.buscarTodasOrganizacoes();
        return ResponseEntity.ok().body(organizacao);
    }

    public ResponseEntity<Organizacao> buscarOrganizacaoPorId(UUID id) {
        Organizacao organizacao = organizacaoRepository.buscarOrganizacaoPorId(id);
        return ResponseEntity.ok().body(organizacao);
    }
    public ResponseEntity<Organizacao> atualizarOrganizacao(UUID id, Organizacao organizacao){
        Organizacao organizacaoNovo = organizacaoRepository.atualizarOrganizacao(id,organizacao);
        return ResponseEntity.ok().body(organizacaoNovo);
    }
    public ResponseEntity<Void> deletarOrganizacao(UUID id){
        organizacaoRepository.excluirOrganizacao(id);
        return ResponseEntity.ok().build();
    }
}
