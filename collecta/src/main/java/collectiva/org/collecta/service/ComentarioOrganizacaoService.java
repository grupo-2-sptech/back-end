package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.ComentarioOrganizacao;
import collectiva.org.collecta.dto.ComentarioOrganizacaoDTO;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoContemElementosException;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import collectiva.org.collecta.mapper.ComentarioOrganizacaoMapper;
import collectiva.org.collecta.repository.ComentarioOrganizacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComentarioOrganizacaoService {
    private final ComentarioOrganizacaoRepository comentarioOrganizacaoRepository;

    public ComentarioOrganizacaoDTO salvarComentario(ComentarioOrganizacaoDTO comentarioOrganizacaoDTO) {
        ComentarioOrganizacao comentarioOrganizacao = ComentarioOrganizacaoMapper.paraEntidade(comentarioOrganizacaoDTO);
        comentarioOrganizacaoRepository.save(comentarioOrganizacao);
        return ComentarioOrganizacaoMapper.paraDTO(comentarioOrganizacao);
    }

    public List<ComentarioOrganizacaoDTO> buscarTodosComentarios() {
        List<ComentarioOrganizacao> comentariosOrganizacao = comentarioOrganizacaoRepository.findAll();
        if (comentariosOrganizacao.isEmpty()) {
            throw new EntidadeNaoContemElementosException("ComentariosOrganizacao");
        }
        return comentariosOrganizacao.stream().map(ComentarioOrganizacaoMapper::paraDTO).collect(Collectors.toList());
    }

    public ComentarioOrganizacaoDTO buscarComentarioPorId(UUID id) {
        return ComentarioOrganizacaoMapper.paraDTO(comentarioOrganizacaoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("ComentariosOrganizacao")));
    }

    public ComentarioOrganizacaoDTO atualizarComentario(UUID id, ComentarioOrganizacaoDTO comentarioOrganizacaoDTO) {
        buscarComentarioPorId(id);
        ComentarioOrganizacao comentarioOrganizacaoNovo = ComentarioOrganizacaoMapper.paraEntidade(comentarioOrganizacaoDTO);
        comentarioOrganizacaoNovo.setId(id);
        comentarioOrganizacaoRepository.save(comentarioOrganizacaoNovo);
        return ComentarioOrganizacaoMapper.paraDTO(comentarioOrganizacaoNovo);
    }

    public void deletarComentario(UUID id) {
        if (!comentarioOrganizacaoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("ComentarioOrganizacao");
        }
        comentarioOrganizacaoRepository.deleteById(id);
    }
}

