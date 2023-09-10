package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Doacao;
import collectiva.org.collecta.dto.DoacaoDTO;
import collectiva.org.collecta.mapper.DoacaoMapper;
import collectiva.org.collecta.repository.DoacaoRepository;
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
public class DoacaoService {
    private final DoacaoRepository doacaoRepository;

    public ResponseEntity<DoacaoDTO> salvarDoacao(DoacaoDTO doacaoDTO) {
        Doacao doacao = DoacaoMapper.paraEntidade(doacaoDTO);
        doacaoRepository.save(doacao);
        doacaoDTO = DoacaoMapper.paraDTO(doacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(doacaoDTO);
    }

    public ResponseEntity<List<DoacaoDTO>> buscarTodasDoacoes() {
        List<Doacao> doacoes = doacaoRepository.findAll();
        if (doacoes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<DoacaoDTO> doacoesDTO = doacoes.stream().map(DoacaoMapper::paraDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(doacoesDTO);
    }

    public ResponseEntity<DoacaoDTO> buscarDoacaoPorId(UUID id) {
        Optional<Doacao> doacao = doacaoRepository.findById(id);
        if (doacao.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        DoacaoDTO doacaoDTO = DoacaoMapper.paraDTO(doacao.get());
        return ResponseEntity.ok().body(doacaoDTO);
    }

    public ResponseEntity<DoacaoDTO> atualizarDoacao(UUID id, DoacaoDTO doacaoDTO) {
        Optional<Doacao> doacaoAntiga = doacaoRepository.findById(id);
        if (doacaoAntiga.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Doacao doacaoExistente = doacaoAntiga.get();
        Doacao doacaoAtualizada = Doacao.builder()
                .id(doacaoExistente.getId())
                .valor(doacaoDTO.getValor())
                .dataHora(doacaoExistente.getDataHora())
                .modoContribuicao(doacaoExistente.getModoContribuicao())
                .build();

        doacaoRepository.save(doacaoAtualizada);
        doacaoDTO = DoacaoMapper.paraDTO(doacaoAtualizada);
        return ResponseEntity.ok().body(doacaoDTO);
    }

    public ResponseEntity<Void> deletarDoacao(UUID id) {
        if (!doacaoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        doacaoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

