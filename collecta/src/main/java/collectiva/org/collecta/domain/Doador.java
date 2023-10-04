package collectiva.org.collecta.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doador extends Conta {
    private String nome;
    private String sobrenome;
    private LocalDateTime dataNascimento;
    private String cpf;

    @OneToMany(mappedBy = "doador")
    private List<ComentarioDoador> comentarios;

    @OneToMany(mappedBy = "doador")
    private List<ContribuicaoMonetaria> contribuicaoMonetarias;

    @OneToMany(mappedBy = "doador")
    private List<ContribuicaoServico> contribuicaoServico;

    @OneToMany(mappedBy = "doador")
    private List<ContribuicaoRecurso> contribuicaoRecursos;

    @OneToMany(mappedBy = "doador")
    private List<Plano> planos;

    @Builder

    public Doador(UUID id, String email, String senha, String telefone, String nome, String sobrenome, LocalDateTime dataNascimento, String cpf) {
        super(id, email, senha, telefone);
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.comentarios = new ArrayList<>();
        this.contribuicaoServico = new ArrayList();
        this.contribuicaoRecursos = new ArrayList();
        this.contribuicaoMonetarias = new ArrayList();
        this.planos = new ArrayList<>();
    }
}
