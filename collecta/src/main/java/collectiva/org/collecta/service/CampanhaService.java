package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Campanha;
import collectiva.org.collecta.repository.CampanhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CampanhaService {
    @Autowired
    CampanhaRepository campanhaRepository;

    public ResponseEntity<Campanha> salvarCampanha(Campanha campanha) {
        campanhaRepository.salvarCampanha(campanha);
        return ResponseEntity.status(HttpStatus.CREATED).body(campanha);
    }

    public ResponseEntity<List<Campanha>> buscarTodasCampanhas() {
        List<Campanha> campanha = campanhaRepository.buscarTodasCampanhas();
        return ResponseEntity.ok().body(campanha);
    }

    public ResponseEntity<Campanha> buscarCampanhaPorId(UUID id) {
        Campanha campanha = campanhaRepository.buscarCampanhaPorId(id);
        return ResponseEntity.ok().body(campanha);
    }
    public ResponseEntity<Campanha> atualizarCampanha(UUID id, Campanha campanha){
        Campanha campanhaNova = campanhaRepository.atualizarCampanha(id,campanha);
        return ResponseEntity.ok().body(campanhaNova);
    }
    public ResponseEntity<Void> deletarCampanha(UUID id){
        campanhaRepository.excluirCampanha(id);
        return ResponseEntity.ok().build();
    }
}
