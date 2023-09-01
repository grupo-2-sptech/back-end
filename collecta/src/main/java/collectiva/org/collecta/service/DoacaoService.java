package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Doacao;
import collectiva.org.collecta.repository.DoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DoacaoService {
    @Autowired
    DoacaoRepository doacaoRepository;

    public ResponseEntity<Doacao> salvarDoacao(Doacao doacao) {
        doacaoRepository.salvarDoacao(doacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(doacao);
    }

    public ResponseEntity<List<Doacao>> buscarTodasDoacoes() {
        List<Doacao> doacao = doacaoRepository.buscarTodasDoacoes();
        return ResponseEntity.ok().body(doacao);
    }

    public ResponseEntity<Doacao> buscarDoacaoPorId(UUID id) {
        Doacao doacao = doacaoRepository.buscarDoacaoPorId(id);
        return ResponseEntity.ok().body(doacao);
    }
    public ResponseEntity<Doacao> atualizarDoacao(UUID id, Doacao doacao){
        Doacao doacaoNovo = doacaoRepository.atualizarDoacao(id,doacao);
        return ResponseEntity.ok().body(doacaoNovo);
    }
    public ResponseEntity<Void> deletarDoacao(UUID id){
        doacaoRepository.excluirDoacao(id);
        return ResponseEntity.ok().build();
    }
}
