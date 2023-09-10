package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.ComentarioOrganizacao;
import collectiva.org.collecta.dto.ComentarioOrganizacaoDTO;
import collectiva.org.collecta.mapper.ComentarioDoadorMapper;
import collectiva.org.collecta.mapper.ComentarioOrganizacaoMapper;
import collectiva.org.collecta.repository.ComentarioOrganizacaoRepository;
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
public class ComentarioOrganizacaoService {
    private final ComentarioOrganizacaoRepository comentarioOrganizacaoRepository;

    public ResponseEntity<ComentarioOrganizacaoDTO> salvarComentario(ComentarioOrganizacaoDTO comentarioOrganizacaoDTO) {
        ComentarioOrganizacao comentarioOrganizacao = ComentarioOrganizacaoMapper.paraEntidade(comentarioOrganizacaoDTO);
        comentarioOrganizacaoRepository.save(comentarioOrganizacao);
        comentarioOrganizacaoDTO = ComentarioOrganizacaoMapper.paraDTO(comentarioOrganizacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioOrganizacaoDTO);
    }

    public ResponseEntity<List<ComentarioOrganizacaoDTO>> buscarTodosComentarios() {
        List<ComentarioOrganizacao> comentariosOrganizacao = comentarioOrganizacaoRepository.findAll();
        if (comentariosOrganizacao.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ComentarioOrganizacaoDTO> comentariosOrganizacaoDTO = comentariosOrganizacao.stream().map(ComentarioOrganizacaoMapper::paraDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(comentariosOrganizacaoDTO);
    }

    public ResponseEntity<ComentarioOrganizacaoDTO> buscarComentarioPorId(UUID id) {
        Optional<ComentarioOrganizacao> comentarioOrganizacao = comentarioOrganizacaoRepository.findById(id);
        if (comentarioOrganizacao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ComentarioOrganizacaoDTO comentarioOrganizacaoDTO = ComentarioOrganizacaoMapper.paraDTO(comentarioOrganizacao.get());
        return ResponseEntity.ok().body(comentarioOrganizacaoDTO);
    }

    public ResponseEntity<ComentarioOrganizacaoDTO> atualizarComentario(UUID id, ComentarioOrganizacaoDTO comentarioOrganizacaoDTO) {
        Optional<ComentarioOrganizacao> comentarioDoadorAntigo = comentarioOrganizacaoRepository.findById(id);
        if (comentarioDoadorAntigo.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ComentarioOrganizacao comentarioDoadorExistente = comentarioDoadorAntigo.get();
        ComentarioOrganizacao comentarioDoadorAtualizado = ComentarioOrganizacao.builder()
                .id(comentarioDoadorExistente.getId())
                .comentario(comentarioOrganizacaoDTO.getComentario())
                .data(comentarioDoadorExistente.getData())
                .tipoConta(comentarioDoadorExistente.getTipoConta())
                .build();

        comentarioOrganizacaoRepository.save(comentarioDoadorAtualizado);
        comentarioOrganizacaoDTO = ComentarioOrganizacaoMapper.paraDTO(comentarioDoadorAtualizado);
        return ResponseEntity.ok().body(comentarioOrganizacaoDTO);
    }

    public ResponseEntity<Void> deletarComentario(UUID id) {
        if (!comentarioOrganizacaoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        comentarioOrganizacaoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

