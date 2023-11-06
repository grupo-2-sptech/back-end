package collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria;

import collectiva.org.collecta.domain.contribuicao.Contribuicao;
import collectiva.org.collecta.domain.conta.Doador.Doador;
import collectiva.org.collecta.domain.financeiroCampanha.FinanceiroCampanha;
import collectiva.org.collecta.enums.FormaPagamento;
import collectiva.org.collecta.enums.StatusContribuicao;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContribuicaoMonetaria extends Contribuicao {
    private BigDecimal valor;
    private Integer parcelas;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;


    @ManyToOne
    @JoinColumn(name = "doador")
    private Doador doador;

    @ManyToOne
    @JoinColumn(name = "financeiroCampanha")
    private FinanceiroCampanha financeiroCampanha;

    @Builder
    public ContribuicaoMonetaria(UUID id, String nome, String descricao, LocalDateTime dataHora, StatusContribuicao statusContribuicao, BigDecimal valor, Integer parcelas, FormaPagamento formaPagamento, Doador doador, FinanceiroCampanha financeiroCampanha) {
        super(id, nome, descricao, dataHora, statusContribuicao);
        this.valor = valor;
        this.parcelas = parcelas;
        this.formaPagamento = formaPagamento;
        this.doador = doador;
        this.financeiroCampanha = financeiroCampanha;
    }
}
