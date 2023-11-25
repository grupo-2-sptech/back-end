package collectiva.org.collecta.domain.conta.doador;

import collectiva.org.collecta.domain.comentario.comentarioDoador.ComentarioDoador;
import collectiva.org.collecta.domain.conta.usuario.Conta;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.ContribuicaoMonetaria;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.ContribuicaoRecurso;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.ContribuicaoServico;
import collectiva.org.collecta.domain.plano.Plano;
import collectiva.org.collecta.domain.postLike.PostLike;
import collectiva.org.collecta.enums.TipoConta;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doador extends Conta {
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private String cpf;

    @OneToMany(mappedBy = "doador")
    private List<ComentarioDoador> comentarios;

    @OneToMany(mappedBy = "doador")
    private List<PostLike> postLikes;

    @OneToMany(mappedBy = "doador")
    private List<ContribuicaoMonetaria> contribuicaoMonetarias;

    @OneToMany(mappedBy = "doador")
    private List<ContribuicaoServico> contribuicaoServico;

    @OneToMany(mappedBy = "doador")
    private List<ContribuicaoRecurso> contribuicaoRecursos;

    @OneToOne(mappedBy = "doador")
    private Plano plano;

    @Builder
    public Doador(UUID id, String email, String senha, String telefone, TipoConta tipoConta, String nome, String sobrenome, LocalDate dataNascimento, String cpf, List<ComentarioDoador> comentarios, List<PostLike> postLikes, List<ContribuicaoMonetaria> contribuicaoMonetarias, List<ContribuicaoServico> contribuicaoServico, List<ContribuicaoRecurso> contribuicaoRecursos, Plano plano) {
        super(id, email, senha, telefone, tipoConta);
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.comentarios = comentarios;
        this.postLikes = postLikes;
        this.contribuicaoMonetarias = contribuicaoMonetarias;
        this.contribuicaoServico = contribuicaoServico;
        this.contribuicaoRecursos = contribuicaoRecursos;
        this.plano = plano;
    }
}
