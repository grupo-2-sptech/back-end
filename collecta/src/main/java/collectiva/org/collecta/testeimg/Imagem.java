package collectiva.org.collecta.testeimg;
import jakarta.persistence.*;


@Entity
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] dadosDaImagem;

    // Getters e Setters
}
