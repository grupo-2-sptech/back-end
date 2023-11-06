package collectiva.org.collecta.domain.recurso.service;

import collectiva.org.collecta.domain.recurso.Recurso;
import collectiva.org.collecta.domain.recurso.dto.RecursoDTO;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import collectiva.org.collecta.domain.recurso.mapper.RecursoMapper;
import collectiva.org.collecta.domain.recurso.repository.RecursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecursoService {
    private final RecursoRepository recursoRepository;

    public RecursoDTO salvarRecurso(RecursoDTO recursoDTO) {
        Recurso recurso = RecursoMapper.paraEntidade(recursoDTO);
        recursoRepository.save(recurso);
        return RecursoMapper.paraDTO(recurso);
    }

    public List<RecursoDTO> buscarTodosRecursos() {
        List<Recurso> recursos = recursoRepository.findAll();
        return recursos.stream().map(RecursoMapper::paraDTO).collect(Collectors.toList());

    }

    public RecursoDTO buscarRecursoPorId(UUID id) {
        return RecursoMapper.paraDTO(recursoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Recurso")));
    }

    public RecursoDTO atualizarRecurso(UUID id, RecursoDTO recursoDTO) {
        buscarRecursoPorId(id);
        Recurso recursoNovo = RecursoMapper.paraEntidade(recursoDTO);
        recursoNovo.setId(id);
        recursoRepository.save(recursoNovo);
        return RecursoMapper.paraDTO(recursoNovo);
    }

    public void deletarRecurso(UUID id) {
        if (!recursoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Recurso");
        }
        recursoRepository.deleteById(id);
    }
}

