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
    private final FinanceiroCampanhaRepository doacaoRepository;

    public ResponseEntity<FinanceiroCampanhaDTO> salvarFinanceiroCampanha(FinanceiroCampanhaDTO doacaoDTO) {
        FinanceiroCampanha doacao = FinanceiroCampanhaMapper.paraEntidade(doacaoDTO);
        doacaoRepository.save(doacao);
        doacaoDTO = FinanceiroCampanhaMapper.paraDTO(doacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(doacaoDTO);
    }

    public ResponseEntity<List<FinanceiroCampanhaDTO>> buscarTodosFinanceirosCampanha() {
        List<FinanceiroCampanha> financeirosCampanha = doacaoRepository.findAll();
        if (financeirosCampanha.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<FinanceiroCampanhaDTO> financeirosCampanhaDTO = financeirosCampanha.stream().map(FinanceiroCampanhaMapper::paraDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(financeirosCampanhaDTO);
    }

    public ResponseEntity<FinanceiroCampanhaDTO> buscarFinanceiroCampanhaPorId(UUID id) {
        Optional<FinanceiroCampanha> doacao = doacaoRepository.findById(id);
        if (doacao.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        FinanceiroCampanhaDTO doacaoDTO = FinanceiroCampanhaMapper.paraDTO(doacao.get());
        return ResponseEntity.ok().body(doacaoDTO);
    }

    public ResponseEntity<FinanceiroCampanhaDTO> atualizarFinanceiroCampanha(UUID id, FinanceiroCampanhaDTO doacaoDTO) {
        Optional<FinanceiroCampanha> doacaoAntiga = doacaoRepository.findById(id);
        if (doacaoAntiga.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        FinanceiroCampanha doacaoExistente = doacaoAntiga.get();
        FinanceiroCampanha doacaoAtualizada = FinanceiroCampanha.builder()
                .id(doacaoExistente.getId())
                .valorMeta(doacaoDTO.getValorMeta())
                .valorAtingido(doacaoDTO.getValorAtingido())
                .metaStatus(MetaStatus.valueOf(doacaoDTO.getMetaStatus()))
                .build();

        doacaoRepository.save(doacaoAtualizada);
        doacaoDTO = FinanceiroCampanhaMapper.paraDTO(doacaoAtualizada);
        return ResponseEntity.ok().body(doacaoDTO);
    }

    public ResponseEntity<Void> deletarFinanceiroCampanha(UUID id) {
        if (!doacaoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        doacaoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

