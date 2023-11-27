package collectiva.org.collecta.utils;

import collectiva.org.collecta.domain.acaoCampanha.AcaoCampanha;
import collectiva.org.collecta.domain.acaoCampanha.dto.CreateAcaoCampanhaDTO;
import collectiva.org.collecta.domain.acaoCampanha.service.AcaoCampanhaService;
import collectiva.org.collecta.domain.relatorio.Relatorio;
import collectiva.org.collecta.domain.relatorio.service.RelatorioService;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class GravaTxt {

  static AcaoCampanhaService acaoCampanhaService;
  static RelatorioService relatorioService;

  private static final String HEADER_RECORD_TYPE = "00";
  private static final String BODY_RECORD_TYPE = "02";
  private static final String TRAILER_RECORD_TYPE = "01";

  public static void gravaRegistro(String registro, String nomeArq) {
    try (BufferedWriter saida = new BufferedWriter(new FileWriter(nomeArq, true))) {
      saida.append(registro).append("\n");
    } catch (IOException erro) {
      System.out.println("Erro ao gravar o arquivo");
      erro.printStackTrace();
    }
  }

  public static void gravaArquivoTxt(List<CreateAcaoCampanhaDTO> lista, String nomeArq) {
    gravaRegistro(montaRegistroHeader(), nomeArq);

    for (CreateAcaoCampanhaDTO acaoCampanhaDTO : lista) {
      String corpo = montaRegistroCorpo(acaoCampanhaDTO);
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
    System.out.println("É um registro de header");

    // Lógica para processar o registro de header
    String nome = registro.substring(2,38);
    String desc = registro.substring(39,48);
    LocalDate data = LocalDate.parse(registro.substring(49,60));
    BigDecimal valor = BigDecimal.valueOf(Long.valueOf(registro.substring(61,69)));
    UUID idRelatorio = UUID.fromString(registro.substring(70,105));

    Relatorio relatorio = relatorioService.buscarRelatorioPorId(idRelatorio);
    AcaoCampanha acaoCampanha = new AcaoCampanha(UUID.randomUUID(),nome,desc,data,valor, relatorio);
    acaoCampanhaService.salvarAcaoCampanha(acaoCampanha, relatorio);
  }

  private static void processaRegistroTrailer(String registro) {
    System.out.println("É um registro de trailer");
    // Lógica para processar o registro de trailer
    int qtdRegDadosGravados = Integer.parseInt(registro.substring(2, 12));
    System.out.println("Quantidade de reg de dados gravados: " + qtdRegDadosGravados);
  }

  private static void processaRegistroCorpo(String registro) {
    System.out.println("É um registro de corpo");
    // Lógica para processar o registro de corpo
  }

  private static String montaRegistroHeader() {
    return HEADER_RECORD_TYPE +
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) +
        "01";
  }

  private static String montaRegistroTrailer(int quantidadeRegistros) {
    return TRAILER_RECORD_TYPE +
        String.format("%010d", quantidadeRegistros);
  }

  private static String montaRegistroCorpo(CreateAcaoCampanhaDTO acaoCampanhaDTO) {
    return BODY_RECORD_TYPE +
        acaoCampanhaDTO.getIdRelatorio().toString() +
        acaoCampanhaDTO.getData().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
        String.format("%012.2f", acaoCampanhaDTO.getValor())+
        acaoCampanhaDTO.getValor()+
        acaoCampanhaDTO.getIdRelatorio();
  }
}
