package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.FinanceiroCampanha;
import collectiva.org.collecta.domain.enums.MetaStatus;
import collectiva.org.collecta.dto.FinanceiroCampanhaDTO;
import collectiva.org.collecta.mapper.FinanceiroCampanhaMapper;
import collectiva.org.collecta.repository.FinanceiroCampanhaRepository;
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
public class FinanceiroCampanhaService {
    private final FinanceiroCampanhaRepository finaceiroCampanhaRepository;

    public ResponseEntity<FinanceiroCampanhaDTO> salvarFinanceiroCampanha(FinanceiroCampanhaDTO finaceiroCampanhaDTO) {
        FinanceiroCampanha finaceiroCampanha = FinanceiroCampanhaMapper.paraEntidade(finaceiroCampanhaDTO);
        finaceiroCampanhaRepository.save(finaceiroCampanha);
        finaceiroCampanhaDTO = FinanceiroCampanhaMapper.paraDTO(finaceiroCampanha);
        return ResponseEntity.status(HttpStatus.CREATED).body(finaceiroCampanhaDTO);
    }

    public ResponseEntity<List<FinanceiroCampanhaDTO>> buscarTodosFinanceirosCampanha() {
        List<FinanceiroCampanha> financeirosCampanha = finaceiroCampanhaRepository.findAll();
        if (financeirosCampanha.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<FinanceiroCampanhaDTO> financeirosCampanhaDTO = financeirosCampanha.stream().map(FinanceiroCampanhaMapper::paraDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(financeirosCampanhaDTO);
    }

    public ResponseEntity<FinanceiroCampanhaDTO> buscarFinanceiroCampanhaPorId(UUID id) {
        Optional<FinanceiroCampanha> finaceiroCampanha = finaceiroCampanhaRepository.findById(id);
        if (finaceiroCampanha.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        FinanceiroCampanhaDTO finaceiroCampanhaDTO = FinanceiroCampanhaMapper.paraDTO(finaceiroCampanha.get());
        return ResponseEntity.ok().body(finaceiroCampanhaDTO);
    }

    public ResponseEntity<FinanceiroCampanhaDTO> atualizarFinanceiroCampanha(UUID id, FinanceiroCampanhaDTO finaceiroCampanhaDTO) {
        Optional<FinanceiroCampanha> finaceiroCampanhaAntiga = finaceiroCampanhaRepository.findById(id);
        if (finaceiroCampanhaAntiga.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        FinanceiroCampanha finaceiroCampanhaExistente = finaceiroCampanhaAntiga.get();
        FinanceiroCampanha finaceiroCampanhaAtualizada = FinanceiroCampanha.builder()
                .id(finaceiroCampanhaExistente.getId())
                .valorMeta(finaceiroCampanhaDTO.getValorMeta())
                .valorAtingido(finaceiroCampanhaDTO.getValorAtingido())
                .metaStatus(finaceiroCampanhaDTO.getMetaStatus())
                .build();

        finaceiroCampanhaRepository.save(finaceiroCampanhaAtualizada);
        finaceiroCampanhaDTO = FinanceiroCampanhaMapper.paraDTO(finaceiroCampanhaAtualizada);
        return ResponseEntity.ok().body(finaceiroCampanhaDTO);
    }

    public ResponseEntity<Void> deletarFinanceiroCampanha(UUID id) {
        if (!finaceiroCampanhaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        finaceiroCampanhaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

