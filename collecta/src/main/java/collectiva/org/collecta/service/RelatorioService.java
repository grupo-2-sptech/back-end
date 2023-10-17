package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Relatorio;
import collectiva.org.collecta.dto.RelatorioDTO;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import collectiva.org.collecta.mapper.RelatorioMapper;
import collectiva.org.collecta.repository.RelatorioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelatorioService {
    private final RelatorioRepository relatorioRepository;

    public RelatorioDTO salvarRelatorio(RelatorioDTO relatorioDTO) {
        Relatorio relatorio = RelatorioMapper.paraEntidade(relatorioDTO);
        relatorioRepository.save(relatorio);
        return RelatorioMapper.paraDTO(relatorio);
    }

    public List<RelatorioDTO> buscarTodosRelatorios() {
        List<Relatorio> relatorios = relatorioRepository.findAll();
        return relatorios.stream().map(RelatorioMapper::paraDTO).collect(Collectors.toList());
    }

    public RelatorioDTO buscarRelatorioPorId(UUID id) {
        return RelatorioMapper.paraDTO(relatorioRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Relatorio")));
    }

    public RelatorioDTO atualizarRelatorio(UUID id, RelatorioDTO relatorioDTO) {
        buscarRelatorioPorId(id);
        Relatorio relatorioNovo = RelatorioMapper.paraEntidade(relatorioDTO);
        relatorioNovo.setId(id);
        relatorioRepository.save(relatorioNovo);
        return RelatorioMapper.paraDTO(relatorioNovo);
    }

    public void deletarRelatorio(UUID id) {
        if (!relatorioRepository.existsById(id)){
            throw new EntidadeNaoEncontradaException("Relatorio");
        }
        relatorioRepository.deleteById(id);
    }
}

