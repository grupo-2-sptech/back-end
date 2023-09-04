package collectiva.org.collecta.service;

import collectiva.org.collecta.controller.CampanhaController;
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
        if(campanha.getNome() == null || campanha.getNome().isEmpty() || campanha.getNome().length() <= 3  ||
                campanha.getDescricao() == null ||campanha.getDescricao().isEmpty() || campanha.getDescricao().length() <= 3 ||
                campanha.getValorArrecadado() == null ||campanha.getValorArrecadado().compareTo(BigDecimal.ZERO) < 0 ||
                campanha.getValorMeta().compareTo(BigDecimal.ZERO) < 0 || campanha.getValorMeta() == null ||
                campanha.getDataInicio() == null ||
                campanha.getDataFim() == null ||
                campanha.getStatus().isEmpty() || campanha.getStatus().length() <= 3 || campanha.getStatus() == null
        ){
            return ResponseEntity.badRequest().build();
        }
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
        if(campanha.getNome() == null || campanha.getNome().isEmpty() || campanha.getNome().length() <= 3  ||
                campanha.getDescricao() == null ||campanha.getDescricao().isEmpty() || campanha.getDescricao().length() <= 3 ||
                campanha.getValorArrecadado() == null ||campanha.getValorArrecadado().compareTo(BigDecimal.ZERO) < 0 ||
                campanha.getValorMeta().compareTo(BigDecimal.ZERO) < 0 || campanha.getValorMeta() == null ||
                campanha.getDataInicio() == null ||
                campanha.getDataFim() == null ||
                campanha.getStatus().isEmpty() || campanha.getStatus().length() <= 3 || campanha.getStatus() == null
        ){
            return ResponseEntity.badRequest().build();
        }
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
        Optional<Campanha> campanha = campanhaRepository.findById(id);
        if(campanha.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        campanhaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

