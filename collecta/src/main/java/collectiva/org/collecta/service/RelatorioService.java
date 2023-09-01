package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Relatorio;
import collectiva.org.collecta.repository.RelatorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RelatorioService {
    @Autowired
    RelatorioRepository relatorioRepository;

    public ResponseEntity<Relatorio> salvarRelatorio(Relatorio relatorio) {
        relatorioRepository.salvarRelatorio(relatorio);
        return ResponseEntity.status(HttpStatus.CREATED).body(relatorio);
    }

    public ResponseEntity<List<Relatorio>> buscarTodasRelatorios() {
        List<Relatorio> relatorio = relatorioRepository.buscarTodosRelatorios();
        return ResponseEntity.ok().body(relatorio);
    }

    public ResponseEntity<Relatorio> buscarRelatorioPorId(UUID id) {
        Relatorio relatorio = relatorioRepository.buscarRelatorioPorId(id);
        return ResponseEntity.ok().body(relatorio);
    }
    public ResponseEntity<Relatorio> atualizarRelatorio(UUID id, Relatorio relatorio){
        Relatorio relatorioNovo = relatorioRepository.atualizarRelatorio(id,relatorio);
        return ResponseEntity.ok().body(relatorioNovo);
    }
    public ResponseEntity<Void> deletarRelatorio(UUID id){
        relatorioRepository.excluirRelatorio(id);
        return ResponseEntity.ok().build();
    }
}
