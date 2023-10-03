package collectiva.org.collecta.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.time.LocalDateTime;
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

    @Builder
    public Doador(UUID id, String email, String senha, String telefone, String nome, String sobrenome, LocalDateTime dataNascimento, String cpf, List<FinanceiroCampanha> financeirosCampanha, List<ComentarioDoador> comentarios) {
        super(id, email, senha, telefone);
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.comentarios = new ArrayList<>();
        this.contribuicaoMonetarias = new ArrayList();
    }
}
