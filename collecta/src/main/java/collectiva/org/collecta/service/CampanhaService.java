package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Campanha;
import collectiva.org.collecta.repository.CampanhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CampanhaService {
    private final CampanhaRepository campanhaRepository;

    public ResponseEntity<Campanha> salvarCampanha(Campanha campanha) {
        campanhaRepository.save(campanha);
        return ResponseEntity.status(HttpStatus.CREATED).body(campanha);
    }

    public ResponseEntity<List<Campanha>> buscarTodasCampanhas() {
        List<Campanha> campanha = campanhaRepository.findAll();
        if(campanha.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(campanha);
    }

    public ResponseEntity<Optional<Campanha>> buscarCampanhaPorId(UUID id) {
        Optional<Campanha> campanha = campanhaRepository.findById(id);
        if(campanha.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(campanha);
    }

    public ResponseEntity<Campanha> atualizarCampanha(UUID id, Campanha campanha) {
        Optional<Campanha> campanhaAntiga = campanhaRepository.findById(id);
        if(campanhaAntiga.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Campanha campanhaExistente = campanhaAntiga.get();
        Campanha campanhaAtualizada = Campanha.builder()
                .id(campanhaExistente.getId())
                .nome(campanha.getNome())
                .descricao(campanha.getDescricao())
                .valorArrecadado(campanha.getValorArrecadado())
                .valorMeta(campanha.getValorMeta())
                .dataInicio(campanha.getDataInicio())
                .dataFim(campanha.getDataFim())
                .status(campanha.getStatus())
                .build();

        campanhaRepository.save(campanhaAtualizada);

        return ResponseEntity.ok().body(campanhaAtualizada);
    }

    public ResponseEntity<Void> deletarCampanha(UUID id) {
        if (!campanhaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        campanhaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

