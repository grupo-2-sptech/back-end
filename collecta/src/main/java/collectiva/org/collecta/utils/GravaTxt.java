package collectiva.org.collecta.utils;

import collectiva.org.collecta.domain.relatorio.Relatorio;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GravaTxt {

  public static void gravaRegistro(String registro, String nomeArq) {
    try (BufferedWriter saida = new BufferedWriter(new FileWriter(nomeArq, true))) {
      saida.append(registro).append("\n");
    } catch (IOException erro) {
      System.out.println("Erro ao gravar o arquivo");
      erro.printStackTrace();
    }
  }

  public static void gravaArquivoTxt(List<Relatorio> lista, String nomeArq) {
    int contaRegDados = 0;

    // Monta o registro de header
    String header = "RELATORIO";
    header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    header += "01";

    // Grava o registro de header
    gravaRegistro(header, nomeArq);

    // Grava os registros de dados (ou registros de corpo)
    for (Relatorio relatorio : lista) {
      String corpo = montaRegistroCorpo(relatorio);
      gravaRegistro(corpo, nomeArq);
      contaRegDados++;
    }

    // Monta e grava o registro de trailer
    String trailer = "01";
    trailer += String.format("%010d", contaRegDados);

    gravaRegistro(trailer, nomeArq);
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
      case "00":
        processaRegistroHeader(registro);
        break;
      case "01":
        processaRegistroTrailer(registro);
        break;
      case "02":
        processaRegistroCorpo(registro);
        break;
      default:
        System.out.println("Registro inválido");
    }
  }

  private static void processaRegistroHeader(String registro) {
    System.out.println("É um registro de header");
    // Lógica para processar o registro de header
    String tipoArquivo = registro.substring(2, 6);
    String anoSemestre = registro.substring(6, 11);
    String dataHoraGeracao = registro.substring(11, 30);
    String versaoLayout = registro.substring(30, 32);
    // Faça o que for necessário com esses dados
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
    String idRelatorio = registro.substring(2, 38);
    String data = registro.substring(38, 48);
    // Continue para os outros campos...
    // Crie uma instância de Relatorio com base nos dados lidos
   // Relatorio relatorio = criarRelatorio(idRelatorio, data, /* outros campos */);
    // Faça o que for necessário com a instância de Relatorio
  }

  private static Relatorio criarRelatorio(String idRelatorio, String data /* outros campos */) {
    // Implemente a lógica para criar uma instância de Relatorio
    // com base nos dados do
    // ...

    return new Relatorio(/* dados do relatório */);
  }

  private static String montaRegistroCorpo(Relatorio relatorio) {
    StringBuilder corpo = new StringBuilder("02");
    corpo.append(relatorio.getId().toString());
    corpo.append(relatorio.getData().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    corpo.append(String.format("%012.2f", relatorio.getValorArrecadado()));
    //corpo.append(String.format("%03d", relatorio.get));
    // Continue para os outros campos...
    return corpo.toString();
  }

  public static void main(String[] args) {
    // Exemplo de importação
    String caminhoDoArquivoImportacao = "caminho/do/seu/arquivo.txt";
    leArquivoTxt(caminhoDoArquivoImportacao);

    // Exemplo de exportação
    String caminhoDoArquivoExportacao = "caminho/do/seu/novo/arquivo.txt";
    List<Relatorio> relatoriosParaExportar = new ArrayList<>();
    // Preencha a lista com instâncias de Relatorio
    // ...
    gravaArquivoTxt(relatoriosParaExportar, caminhoDoArquivoExportacao);
  }
}

