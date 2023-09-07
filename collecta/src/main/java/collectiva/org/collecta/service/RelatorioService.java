package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Relatorio;
import collectiva.org.collecta.repository.RelatorioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RelatorioService {
    private final RelatorioRepository relatorioRepository;

    public ResponseEntity<Relatorio> salvarRelatorio(Relatorio relatorio) {
        relatorioRepository.save(relatorio);
        return ResponseEntity.status(HttpStatus.CREATED).body(relatorio);
    }

    public ResponseEntity<List<Relatorio>> buscarTodosRelatorios() {
        List<Relatorio> relatorio = relatorioRepository.findAll();
        if (relatorio.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(relatorio);
    }

    public ResponseEntity<Optional<Relatorio>> buscarRelatorioPorId(UUID id) {
        Optional<Relatorio> relatorio = relatorioRepository.findById(id);
        if (relatorio.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(relatorio);
    }

    public ResponseEntity<Relatorio> atualizarRelatorio(UUID id, Relatorio relatorio) {
        Optional<Relatorio> relatorioAntigo = relatorioRepository.findById(id);
        if (relatorioAntigo.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Relatorio relatorioExistente = relatorioAntigo.get();
        Relatorio relatorioAtualizado = Relatorio.builder()
                .id(relatorioExistente.getId())
                .data(relatorio.getData())
                .valorArrecadado(relatorio.getValorArrecadado())
                .quantidadeDoacoes(relatorio.getQuantidadeDoacoes())
                .build();
        relatorioRepository.save(relatorioAtualizado);

        return ResponseEntity.ok().body(relatorioAtualizado);
    }

    public ResponseEntity<Void> deletarRelatorio(UUID id) {
        if (relatorioRepository.existsById(id)){
            return ResponseEntity.badRequest().build();
        }
        relatorioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

