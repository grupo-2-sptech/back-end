package collectiva.org.collecta.utils;

public class FilaObj<T> {
  // Atributos
  private int tamanho;
  private T[] fila;

  // Construtor
  public FilaObj(int capacidade) {
    this.tamanho = 0;
    this.fila = (T[]) new Object[capacidade];
  }

  // Métodos

  /* Método isEmpty() - retorna true se a fila está vazia e false caso contrário */
  public boolean isEmpty() {
    return this.tamanho == 0;
  }

  /* Método isFull() - retorna true se a fila está cheia e false caso contrário */
  public boolean isFull() {
    return this.tamanho == this.fila.length;
  }

  /* Método insert - recebe um elemento e insere esse elemento na fila
                     no índice tamanho, e incrementa tamanho
                     Lançar IllegalStateException caso a fila esteja cheia
   */
  public void insert(T info) {
    if (this.isFull()) {
      throw new IllegalStateException("A fila está cheia");
    }

    this.fila[this.tamanho++] = info;
  }

  /* Método peek - retorna o primeiro elemento da fila, sem removê-lo */
  public T peek() {
    if (this.isEmpty()) {
      return null;
    }

    return this.fila[0];
  }

  /* Método poll - remove e retorna o primeiro elemento da fila, se a fila não estiver
     vazia. Quando um elemento é removido, a fila "anda", e tamanho é decrementado
     Depois que a fila andar, "limpar" o ex-último elemento da fila, atribuindo null
   */
  public T poll() {
    if (this.isEmpty()) {
      return null;
    }

    T info = this.fila[0];

    for (int i = 0; i < this.tamanho - 1; i++) {
      this.fila[i] = this.fila[i + 1];
    }

    this.fila[this.tamanho - 1] = null;
    this.tamanho--;

    return info;
  }

  /* Método exibe() - exibe o conteúdo da fila */
  public void exibe() {
    for (int i = 0; i < this.tamanho; i++) {
      System.out.println(this.fila[i]);
    }
  }

  /* Usado nos testes  - complete para que fique certo */
  public int getTamanho(){
    return this.tamanho;
  }
}
