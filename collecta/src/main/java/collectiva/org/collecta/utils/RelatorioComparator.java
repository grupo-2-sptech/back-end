package collectiva.org.collecta.utils;

import collectiva.org.collecta.domain.relatorio.dto.RelatorioDTO;

import java.util.Comparator;

public class RelatorioComparator implements Comparator<RelatorioDTO> {
  @Override
  public int compare(RelatorioDTO relatorio1, RelatorioDTO relatorio2) {
    return relatorio1.getData().compareTo(relatorio2.getData());
  }
}