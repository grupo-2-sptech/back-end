package collectiva.org.collecta.utils;

public class PilhaObj<T> {

  // Atributos
  private T[] array;
  private int topo;
  private int capacidade;

  // Construtor
  public PilhaObj(int capacidade) {
    this.capacidade = capacidade;
    this.array = (T[]) new Object[capacidade];
    this.topo = -1;
  }

  // Métodos

  /* Método isEmpty() - retorna true se a pilha está vazia e false caso contrário */
  public boolean isEmpty() {
    return this.topo == -1;
  }

  /* Método isFull() - retorna true se a pilha está cheia e false caso contrário */
  public boolean isFull() {
    return this.topo == this.capacidade - 1;
  }

  /* Método push - insere um elemento no topo da pilha */
  public void push(T info) {
    if (this.isFull()) {
      throw new IllegalStateException("A pilha está cheia");
    }

    this.topo++;
    this.array[this.topo] = info;
  }

  /* Método pop - remove e retorna o elemento no topo da pilha */
  public T pop() {
    if (this.isEmpty()) {
      return null;
    }

    T info = this.array[this.topo];
    this.topo--;

    return info;
  }

  /* Método peek - retorna o elemento no topo da pilha, sem removê-lo */
  public T peek() {
    if (this.isEmpty()) {
      return null;
    }

    return this.array[this.topo];
  }
}
