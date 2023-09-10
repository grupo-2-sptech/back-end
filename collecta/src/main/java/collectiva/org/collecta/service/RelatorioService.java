package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Relatorio;
import collectiva.org.collecta.dto.RelatorioDTO;
import collectiva.org.collecta.mapper.RelatorioMapper;
import collectiva.org.collecta.repository.RelatorioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelatorioService {
    private final RelatorioRepository relatorioRepository;

    public ResponseEntity<RelatorioDTO> salvarRelatorio(RelatorioDTO relatorioDTO) {
        Relatorio relatorio = RelatorioMapper.paraEntidade(relatorioDTO);
        relatorioRepository.save(relatorio);
        relatorioDTO = RelatorioMapper.paraDTO(relatorio);
        return ResponseEntity.status(HttpStatus.CREATED).body(relatorioDTO);
    }

    public ResponseEntity<List<RelatorioDTO>> buscarTodosRelatorios() {
        List<Relatorio> relatorios = relatorioRepository.findAll();
        if (relatorios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<RelatorioDTO> relatoriosDTO = relatorios.stream().map(RelatorioMapper::paraDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(relatoriosDTO);
    }

    public ResponseEntity<RelatorioDTO> buscarRelatorioPorId(UUID id) {
        Optional<Relatorio> relatorio = relatorioRepository.findById(id);
        if (relatorio.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        RelatorioDTO relatorioDTO = RelatorioMapper.paraDTO(relatorio.get());
        return ResponseEntity.ok().body(relatorioDTO);
    }

    public ResponseEntity<RelatorioDTO> atualizarRelatorio(UUID id, RelatorioDTO relatorioDTO) {
        Optional<Relatorio> relatorioAntigo = relatorioRepository.findById(id);
        if (relatorioAntigo.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Relatorio relatorioExistente = relatorioAntigo.get();
        Relatorio relatorioAtualizado = Relatorio.builder()
                .id(relatorioExistente.getId())
                .data(relatorioExistente.getData())
                .valorArrecadado(relatorioDTO.getValorArrecadado())
                .quantidadeDoacoes(relatorioDTO.getQuantidadeDoacoes())
                .build();
        relatorioRepository.save(relatorioAtualizado);
        relatorioDTO = RelatorioMapper.paraDTO(relatorioAtualizado);
        return ResponseEntity.ok().body(relatorioDTO);
    }

    public ResponseEntity<Void> deletarRelatorio(UUID id) {
        if (!relatorioRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        relatorioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

