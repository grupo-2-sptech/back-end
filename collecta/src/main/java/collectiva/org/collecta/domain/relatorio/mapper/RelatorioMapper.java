package collectiva.org.collecta.domain.relatorio.mapper;

import collectiva.org.collecta.domain.acaoCampanha.mapper.AcaoCampanhaMapper;
import collectiva.org.collecta.domain.relatorio.Relatorio;
import collectiva.org.collecta.domain.relatorio.dto.AssociationRelatorioDTO;
import collectiva.org.collecta.domain.relatorio.dto.CreateRelatorioDTO;
import collectiva.org.collecta.domain.relatorio.dto.GeneratorRelatorioDTO;
import collectiva.org.collecta.domain.relatorio.dto.ResponseRelatorioDTO;

import java.time.LocalDateTime;

public class RelatorioMapper {
    private RelatorioMapper() {
    }

    public static Relatorio paraEntidade(CreateRelatorioDTO relatorioDTO) {
        return Relatorio.builder()
                .valorArrecadado(relatorioDTO.getValorArrecadado())
                .valorMeta(relatorioDTO.getValorMeta())
                .valorRestante(relatorioDTO.getValorRestante())
                .visualizacoes(relatorioDTO.getVisualizacoes())
                .contribuicoesServicos(relatorioDTO.getContribuicoesServicos())
                .contribuicoesRecurso(relatorioDTO.getContribuicoesRecurso())
                .contribuicoesMonetarias(relatorioDTO.getContribuicoesMonetarias())
                .data(LocalDateTime.now())
                .build();
    }

    public static Relatorio paraGeradorEntidade(GeneratorRelatorioDTO relatorioDTO) {
        return Relatorio.builder()
                .valorArrecadado(relatorioDTO.getValorArrecadado())
                .valorMeta(relatorioDTO.getValorMeta())
                .valorRestante(relatorioDTO.getValorRestante())
                .visualizacoes(relatorioDTO.getVisualizacoes())
                .contribuicoesServicos(relatorioDTO.getContribuicoesServicos())
                .contribuicoesRecurso(relatorioDTO.getContribuicoesRecurso())
                .contribuicoesMonetarias(relatorioDTO.getContribuicoesMonetarias())
                .data(LocalDateTime.now())
                .build();
    }

    public static ResponseRelatorioDTO paraDTO(Relatorio relatorio) {
        return ResponseRelatorioDTO.builder()
                .id(relatorio.getId())
                .valorArrecadado(relatorio.getValorArrecadado())
                .valorMeta(relatorio.getValorMeta())
                .valorRestante(relatorio.getValorRestante())
                .visualizacoes(relatorio.getVisualizacoes())
                .contribuicoesServicos(relatorio.getContribuicoesServicos())
                .contribuicoesRecurso(relatorio.getContribuicoesRecurso())
                .contribuicoesMonetarias(relatorio.getContribuicoesMonetarias())
                .data(LocalDateTime.now())
                .acoes(relatorio.getAcoes().stream().map(AcaoCampanhaMapper::paraAssociacaoDTO).toList())
                .build();
    }

    public static AssociationRelatorioDTO paraAssociacaoDTO(Relatorio relatorio) {
        return AssociationRelatorioDTO.builder()
                .id(relatorio.getId())
                .valorArrecadado(relatorio.getValorArrecadado())
                .valorMeta(relatorio.getValorMeta())
                .valorRestante(relatorio.getValorRestante())
                .visualizacoes(relatorio.getVisualizacoes())
                .contribuicoesServicos(relatorio.getContribuicoesServicos())
                .contribuicoesRecurso(relatorio.getContribuicoesRecurso())
                .contribuicoesMonetarias(relatorio.getContribuicoesMonetarias())
                .data(LocalDateTime.now())
                .build();
    }

    public static GeneratorRelatorioDTO paraDTOGerador(Relatorio relatorio) {
        return GeneratorRelatorioDTO.builder()
                .valorArrecadado(relatorio.getValorArrecadado())
                .valorMeta(relatorio.getValorMeta())
                .valorRestante(relatorio.getValorRestante())
                .visualizacoes(relatorio.getVisualizacoes())
                .contribuicoesServicos(relatorio.getContribuicoesServicos())
                .contribuicoesRecurso(relatorio.getContribuicoesRecurso())
                .contribuicoesMonetarias(relatorio.getContribuicoesMonetarias())
                .build();
    }
}
