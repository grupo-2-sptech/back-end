package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Doador;
import collectiva.org.collecta.dto.DoadorDTO;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoContemElementosException;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import collectiva.org.collecta.mapper.DoadorMapper;
import collectiva.org.collecta.repository.DoadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoadorService {
    private final DoadorRepository doadorRepository;

    public DoadorDTO salvarDoador(DoadorDTO doadorDTO) {
        Doador doador = DoadorMapper.paraEntidade(doadorDTO);
        doadorRepository.save(doador);
        return DoadorMapper.paraDTO(doador);
    }

    public List<DoadorDTO> buscarTodosDoadores() {
        List<Doador> doadores = doadorRepository.findAll();
        if (doadores.isEmpty()) {
            throw new EntidadeNaoContemElementosException("Doador");
        }
        return doadores.stream().map(DoadorMapper::paraDTO).collect(Collectors.toList());
    }

    public DoadorDTO buscarDoadorPorId(UUID id) {
        return DoadorMapper.paraDTO(doadorRepository.findById(id).orElseThrow(
                ()-> new EntidadeNaoEncontradaException("Doador")));
    }

    public DoadorDTO atualizarDoador(UUID id, DoadorDTO doadorDTO) {
        buscarDoadorPorId(id);
        Doador doadorNovo = DoadorMapper.paraEntidade(doadorDTO);
        doadorNovo.setId(id);
        doadorRepository.save(doadorNovo);
        return DoadorMapper.paraDTO(doadorNovo);
    }

    public void deletarDoador(UUID id) {
        if (!doadorRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Doador");
        }
        doadorRepository.deleteById(id);
    }
}

