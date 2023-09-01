package collectiva.org.collecta.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Doador extends Conta {
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private String cpf;

    @OneToMany(mappedBy = "doador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Doacao> doacoes = new ArrayList<Doacao>();

    @OneToMany(mappedBy = "doador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComentarioDoador> comentarios = new ArrayList<>();

}
