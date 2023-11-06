package collectiva.org.collecta.domain.comentario.comentarioDoador.service;

import collectiva.org.collecta.domain.comentario.comentarioDoador.ComentarioDoador;
import collectiva.org.collecta.domain.comentario.comentarioDoador.dto.ComentarioDoadorDTO;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import collectiva.org.collecta.domain.comentario.comentarioDoador.mapper.ComentarioDoadorMapper;
import collectiva.org.collecta.domain.comentario.comentarioDoador.repository.ComentarioDoadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComentarioDoadorService {
    private final ComentarioDoadorRepository comentarioDoadorRepository;

    public ComentarioDoadorDTO salvarComentario(ComentarioDoadorDTO comentarioDoadorDTO) {
        ComentarioDoador comentarioDoador = ComentarioDoadorMapper.paraEntidade(comentarioDoadorDTO);
        comentarioDoadorRepository.save(comentarioDoador);
        return ComentarioDoadorMapper.paraDTO(comentarioDoador);
    }

    public List<ComentarioDoadorDTO> buscarTodosComentarios() {
        List<ComentarioDoador> comentariosDoador = comentarioDoadorRepository.findAll();
        return comentariosDoador.stream().map(ComentarioDoadorMapper::paraDTO).collect(Collectors.toList());
    }


    public ComentarioDoadorDTO buscarComentarioPorId(UUID id) {
        return ComentarioDoadorMapper.paraDTO(comentarioDoadorRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("ComentarioDoador")));
    }

    public ComentarioDoadorDTO atualizarComentario(UUID id, ComentarioDoadorDTO comentarioDoadorDTO) {
        buscarComentarioPorId(id);
        ComentarioDoador comentarioDoadorNovo = ComentarioDoadorMapper.paraEntidade(comentarioDoadorDTO);
        comentarioDoadorNovo.setId(id);
        comentarioDoadorRepository.save(comentarioDoadorNovo);
        return ComentarioDoadorMapper.paraDTO(comentarioDoadorNovo);
    }

    public void deletarComentario(UUID id) {
        if (!comentarioDoadorRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("ComentarioDoador");
        }
        comentarioDoadorRepository.deleteById(id);
    }
}
