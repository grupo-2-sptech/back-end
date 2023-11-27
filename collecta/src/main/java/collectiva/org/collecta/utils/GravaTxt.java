package collectiva.org.collecta.utils;

import collectiva.org.collecta.domain.acaoCampanha.AcaoCampanha;
import collectiva.org.collecta.domain.acaoCampanha.dto.CreateAcaoCampanhaDTO;
import collectiva.org.collecta.domain.acaoCampanha.service.AcaoCampanhaService;
import collectiva.org.collecta.domain.relatorio.Relatorio;
import collectiva.org.collecta.domain.relatorio.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class GravaTxt {

  static AcaoCampanhaService acaoCampanhaService;
  static RelatorioService relatorioService;

  private static final String HEADER_RECORD_TYPE = "00";
  private static final String BODY_RECORD_TYPE = "02";
  private static final String TRAILER_RECORD_TYPE = "01";

  @Autowired
  public GravaTxt(AcaoCampanhaService acaoCampanhaService, RelatorioService relatorioService) {
    this.acaoCampanhaService = acaoCampanhaService;
    this.relatorioService = relatorioService;
  }

  public static void gravaRegistro(String registro, String nomeArq) {
    try (BufferedWriter saida = new BufferedWriter(new FileWriter(nomeArq, true))) {
      saida.append(registro).append("\n");
    } catch (IOException erro) {
      System.out.println("Erro ao gravar o arquivo");
      erro.printStackTrace();
    }
  }

  public static void gravaArquivoTxt(List<AcaoCampanha> lista, String nomeArq) {
    gravaRegistro(montaRegistroHeader(), nomeArq);

    for (AcaoCampanha acaoCampanha : lista) {
      String corpo = montaRegistroCorpo(acaoCampanha);
      gravaRegistro(corpo, nomeArq);
    }

    gravaRegistro(montaRegistroTrailer(lista.size()), nomeArq);

  }

  public static void leArquivoTxt(String nomeArq) {
    try (BufferedReader entrada = new BufferedReader(new FileReader(nomeArq))) {
      String registro;

      while ((registro = entrada.readLine()) != null) {
        processaRegistro(registro);
      }

    } catch (IOException erro) {
      System.out.println("Erro ao ler o arquivo");
      erro.printStackTrace();
    }
  }

  private static void processaRegistro(String registro) {
    String tipoRegistro = registro.substring(0, 2);

    switch (tipoRegistro) {
      case HEADER_RECORD_TYPE:
        processaRegistroHeader(registro);
        break;
      case TRAILER_RECORD_TYPE:
        processaRegistroTrailer(registro);
        break;
      case BODY_RECORD_TYPE:
        processaRegistroCorpo(registro);
        break;
      default:
        System.out.println("Registro inválido");
    }
  }

  private static void processaRegistroHeader(String registro) {
    // Lógica para processar o registro de header
    String dataFormatada = registro.substring(3, 23).trim();
    System.out.println("Data do Header: " + dataFormatada);
  }

  private static void processaRegistroTrailer(String registro) {
    // Lógica para processar o registro de trailer
    int qtdRegDadosGravados = Integer.parseInt(registro.substring(2, 12).trim());
    System.out.println("Quantidade de registros de dados gravados: " + qtdRegDadosGravados);
  }

  private static void processaRegistroCorpo(String registro) {
    // Lógica para processar o registro de corpo
    String nome = registro.substring(2, 17).trim();
    String desc = registro.substring(18, 47).trim();
    LocalDate data = LocalDate.parse(registro.substring(48, 58), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    BigDecimal valor = new BigDecimal(registro.substring(59, 74).trim());
    UUID idRelatorio = UUID.fromString(registro.substring(74, 110).trim());
    System.out.println(idRelatorio);
    Relatorio relatorio = relatorioService.buscarRelatorioPorId(idRelatorio);
    AcaoCampanha acaoCampanha = new AcaoCampanha(UUID.randomUUID(), nome, desc, data, valor, relatorio);
    acaoCampanhaService.salvarAcaoCampanha(acaoCampanha, relatorio);
  }


  private static String montaRegistroHeader() {
    String dataFormatada = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    String tipoArquivo = "AÇÕES";
    return String.format("%-2s %-2s %-20s %-2s", HEADER_RECORD_TYPE, tipoArquivo, dataFormatada, "01");
  }

  private static String montaRegistroTrailer(int quantidadeRegistros) {
    String quantidadeFormatada = String.format("%010d", quantidadeRegistros);
    return String.format("%-2s %-20s", TRAILER_RECORD_TYPE, quantidadeFormatada);
  }



  private static String montaRegistroCorpo(AcaoCampanha acaoCampanhaDTO) {
    String nomeFormatado = String.format("%-15s", acaoCampanhaDTO.getNome());
    String descricaoFormatada = String.format("%-30s", acaoCampanhaDTO.getDescricao());
    String dataFormatada = String.format("%-10s", acaoCampanhaDTO.getData().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    String valorFormatado = String.format("%-15s", acaoCampanhaDTO.getValor());
    String idRelatorioFormatado = String.format("%-25s", acaoCampanhaDTO.getRelatorio().getId().toString());

    return BODY_RECORD_TYPE + " " + nomeFormatado + descricaoFormatada + dataFormatada + " " + valorFormatado + idRelatorioFormatado;
  }




}
