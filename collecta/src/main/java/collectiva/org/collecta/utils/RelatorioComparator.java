package collectiva.org.collecta.utils;

import collectiva.org.collecta.domain.relatorio.dto.ResponseRelatorioDTO;

import java.util.Comparator;

public class RelatorioComparator implements Comparator<ResponseRelatorioDTO> {
    @Override
    public int compare(ResponseRelatorioDTO relatorio1, ResponseRelatorioDTO relatorio2) {
        return relatorio1.getData().compareTo(relatorio2.getData());
    }
}
