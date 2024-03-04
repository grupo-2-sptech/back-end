package collectiva.org.collecta.domain.relatorio.service;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.campanha.service.CampanhaService;
import collectiva.org.collecta.domain.relatorio.Relatorio;
import collectiva.org.collecta.domain.relatorio.dto.CreateRelatorioDTO;
import collectiva.org.collecta.domain.relatorio.dto.GeneratorRelatorioDTO;
import collectiva.org.collecta.domain.relatorio.dto.ResponseRelatorioDTO;
import collectiva.org.collecta.domain.relatorio.mapper.RelatorioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RelatorioServiceFacade {
    private final RelatorioService relatorioService;
    private final CampanhaService campanhaService;

    public List<ResponseRelatorioDTO> buscarTodosRelatorios() {
        return relatorioService.buscarTodosRelatorios().stream().map
                (RelatorioMapper::paraDTO).toList();
    }

    public ResponseRelatorioDTO buscarRelatorioPorId(UUID id) {
        return RelatorioMapper.paraDTO(relatorioService.buscarRelatorioPorId(id));
    }

    public ResponseRelatorioDTO criarRelatorio(UUID idEventoCampanha, CreateRelatorioDTO relatorioDTO) {
        Campanha campanha = campanhaService.buscarCampanhaPorId(idEventoCampanha);
        Relatorio relatorio = RelatorioMapper.paraEntidade(relatorioDTO);
        return RelatorioMapper.paraDTO(relatorioService.criarRelatorio(relatorio, campanha));
    }

    public GeneratorRelatorioDTO gerarRelatorioPorCampanha(UUID idCampanha, LocalDateTime inicio, LocalDateTime fim) {
        return RelatorioMapper.paraDTOGerador(relatorioService.gerarRelatorioPorCampanha(idCampanha, inicio, fim));
    }

}
