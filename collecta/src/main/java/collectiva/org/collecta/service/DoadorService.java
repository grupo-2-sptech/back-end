package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Doador;
import collectiva.org.collecta.repository.DoadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DoadorService {
    @Autowired
    DoadorRepository doadorRepository;

    public ResponseEntity<Doador> salvarDoador(Doador doador) {
        doadorRepository.salvarDoador(doador);
        return ResponseEntity.status(HttpStatus.CREATED).body(doador);
    }

    public ResponseEntity<List<Doador>> buscarTodosDoadores() {
        List<Doador> doador = doadorRepository.buscarTodosDoadores();
        return ResponseEntity.ok().body(doador);
    }

    public ResponseEntity<Doador> buscarDoadorPorId(UUID id) {
        Doador doador = doadorRepository.buscarDoadorPorId(id);
        return ResponseEntity.ok().body(doador);
    }
    public ResponseEntity<Doador> atualizarDoador(UUID id, Doador doador){
        Doador doadorNovo = doadorRepository.atualizarDoador(id,doador);
        return ResponseEntity.ok().body(doadorNovo);
    }
    public ResponseEntity<Void> deletarDoador(UUID id){
        doadorRepository.excluirDoador(id);
        return ResponseEntity.ok().build();
    }
}
