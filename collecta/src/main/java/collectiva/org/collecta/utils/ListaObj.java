package collectiva.org.collecta.utils;

import collectiva.org.collecta.domain.relatorio.dto.ResponseRelatorioDTO;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class ListaObj<T> {

    private T[] vetor;

    private int nroElem;

    public ListaObj(int tamanho) {
        vetor = (T[]) new Object[tamanho];
        nroElem = 0;
    }

    public void adiciona(T elemento) {
        if (nroElem >= vetor.length) {
            System.out.println("Lista cheia!");
            ;
        } else {
            vetor[nroElem++] = elemento;
        }
    }

    public int busca(T elementoBuscado) {
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(elementoBuscado)) {
                return i;
            }
        }
        return -1;
    }

    public boolean removePeloIndice(int indice) {
        if (indice < 0 || indice >= nroElem) {
            System.out.println("\nÍndice inválido!");
            return false;
        }

        for (int i = indice; i < nroElem - 1; i++) {
            vetor[i] = vetor[i + 1];
        }

        nroElem--;
        return true;
    }

    public boolean removeElemento(T elementoARemover) {
        return removePeloIndice(busca(elementoARemover));
    }

    // 08) Método  int getTamanho() {
    public int getTamanho() {
        return nroElem;
    }

    public T getElemento(int indice) {
        return vetor[indice];
    }


    public void limpa() {
        nroElem = 0;
    }

    public void exibe() {
        if (nroElem == 0) {
            System.out.println("\nA lista está vazia.");
        } else {
            System.out.println("\nElementos da lista:");
            for (int i = 0; i < nroElem; i++) {
                System.out.println(vetor[i]);
            }
        }
    }

    public void ordenarPorCampo(Comparator<T> comparator) {
        if (nroElem > 1) {
            for (int i = 0; i < nroElem - 1; i++) {
                int minIndex = i;

                for (int j = i + 1; j < nroElem; j++) {
                    if (comparator.compare(vetor[j], vetor[minIndex]) < 0) {
                        minIndex = j;
                    }
                }

                if (minIndex != i) {
                    T temp = vetor[i];
                    vetor[i] = vetor[minIndex];
                    vetor[minIndex] = temp;
                }
            }
        }
    }

    public ResponseRelatorioDTO pesquisaBinaria(ListaObj lista, LocalDateTime dataAlvo) {
        int esquerda = 0;
        int direita = nroElem - 1;

        while (esquerda <= direita) {
            int meio = esquerda + (direita - esquerda) / 2;
            ResponseRelatorioDTO relatorioNoMeio = (ResponseRelatorioDTO) vetor[meio];

            int comparacao = relatorioNoMeio.getData().compareTo(dataAlvo);

            if (comparacao == 0) {
                return relatorioNoMeio;
            } else if (comparacao < 0) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }

        return null;
    }

    public static void gravaArquivoCsv(ListaObj<ResponseRelatorioDTO> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";


        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }


        try {
            for (int i = 0; i < lista.getTamanho(); i++) {

                ResponseRelatorioDTO relatorio = lista.getElemento(i);
                saida.format("%s;%.2f;%d\n", relatorio.getData(), relatorio.getValorArrecadado(),
                        relatorio.getQuantidadeFinanceirosCampanha());
            }
        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }

    }
}



