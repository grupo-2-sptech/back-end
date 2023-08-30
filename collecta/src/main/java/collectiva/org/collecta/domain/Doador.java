package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
