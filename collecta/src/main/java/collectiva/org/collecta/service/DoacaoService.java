package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Doacao;
import collectiva.org.collecta.repository.DoacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoacaoService {
    private final DoacaoRepository doacaoRepository;

    public ResponseEntity<Doacao> salvarDoacao(Doacao doacao) {
        doacaoRepository.save(doacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(doacao);
    }

    public ResponseEntity<List<Doacao>> buscarTodasDoacoes() {
        List<Doacao> doacao = doacaoRepository.findAll();
        if (doacao.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(doacao);
    }

    public ResponseEntity<Optional<Doacao>> buscarDoacaoPorId(UUID id) {
        Optional<Doacao> doacao = doacaoRepository.findById(id);
        if (doacao.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(doacao);
    }

    public ResponseEntity<Doacao> atualizarDoacao(UUID id, Doacao doacao) {
        Optional<Doacao> doacaoAntiga = doacaoRepository.findById(id);
        Doacao doacaoExistente = doacaoAntiga.get();

        Doacao doacaoAtualizada = Doacao.builder()
                .id(doacaoExistente.getId())
                .valor(doacao.getValor())
                .dataHora(doacao.getDataHora())
                .modoContribuicao(doacao.getModoContribuicao())
                .build();

        doacaoRepository.save(doacaoAtualizada);

        return ResponseEntity.ok().body(doacaoAtualizada);
    }

    public ResponseEntity<Void> deletarDoacao(UUID id) {
        if (!doacaoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        doacaoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

