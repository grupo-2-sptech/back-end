package collectiva.org.collecta.domain.relatorio.mapper;

import collectiva.org.collecta.domain.relatorio.Relatorio;
import collectiva.org.collecta.domain.relatorio.dto.CreateRelatorioDTO;
import collectiva.org.collecta.domain.relatorio.dto.ResponseRelatorioDTO;

import java.time.LocalDateTime;

public class RelatorioMapper {
    private RelatorioMapper() {
    }
    public static Relatorio paraEntidade(CreateRelatorioDTO relatorioDTO){
        return Relatorio.builder()
                .valorArrecadado(relatorioDTO.getValorArrecadado())
                .quantidadeFinanceirosCampanha(relatorioDTO.getQuantidadeFinanceirosCampanha())
                .data(LocalDateTime.now())
                .build();
    }

    public static ResponseRelatorioDTO paraDTO(Relatorio relatorio){
        return ResponseRelatorioDTO.builder()
                .id(relatorio.getId())
                .valorArrecadado(relatorio.getValorArrecadado())
                .quantidadeFinanceirosCampanha(relatorio.getQuantidadeFinanceirosCampanha())
                .data(relatorio.getData())
                .build();
    }
}
