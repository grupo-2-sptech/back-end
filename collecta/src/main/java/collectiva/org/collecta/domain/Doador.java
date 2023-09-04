package collectiva.org.collecta.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
public class Doador extends Conta {
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private String cpf;

    @OneToMany(mappedBy = "doador", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Doacao> doacoes = new ArrayList<Doacao>();

    @OneToMany(mappedBy = "doador", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ComentarioDoador> comentarios = new ArrayList<>();

    @Builder
    public Doador(UUID id, String email, String senha, String telefone, String nome, String sobrenome, Date dataNascimento, String cpf, List<Doacao> doacoes, List<ComentarioDoador> comentarios) {
        super(id, email, senha, telefone);
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.doacoes = doacoes;
        this.comentarios = comentarios;
    }
    public Doador() {
    }
}
