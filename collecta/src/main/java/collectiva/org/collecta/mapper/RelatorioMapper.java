package collectiva.org.collecta.mapper;

import collectiva.org.collecta.domain.Relatorio;
import collectiva.org.collecta.dto.RelatorioDTO;

import java.time.LocalDateTime;

public class RelatorioMapper {
    private RelatorioMapper() {
    }
    public static Relatorio paraEntidade(RelatorioDTO relatorioDTO){
        return Relatorio.builder()
                .valorArrecadado(relatorioDTO.getValorArrecadado())
                .quantidadeDoacoes(relatorioDTO.getQuantidadeDoacoes())
                .data(LocalDateTime.now())
                .build();
    }

    public static RelatorioDTO paraDTO(Relatorio relatorio){
        return RelatorioDTO.builder()
                .id(relatorio.getId())
                .valorArrecadado(relatorio.getValorArrecadado())
                .quantidadeDoacoes(relatorio.getQuantidadeDoacoes())
                .data(relatorio.getData())
                .build();
    }
}
