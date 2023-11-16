package collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria;

import collectiva.org.collecta.domain.contribuicao.Contribuicao;
import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.financeiroCampanha.FinanceiroCampanha;
import collectiva.org.collecta.domain.pagamento.Pagamento;
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
    private Doador doador;

    @ManyToOne
    private FinanceiroCampanha financeiroCampanha;

    @OneToOne
    private Pagamento pagamento;

    @Builder

    public ContribuicaoMonetaria(UUID id, String nome, String descricao, LocalDateTime dataHora, StatusContribuicao statusContribuicao, BigDecimal valor, Integer parcelas, FormaPagamento formaPagamento, Doador doador, FinanceiroCampanha financeiroCampanha, Pagamento pagamento) {
        super(id, nome, descricao, dataHora, statusContribuicao);
        this.valor = valor;
        this.parcelas = parcelas;
        this.formaPagamento = formaPagamento;
        this.doador = doador;
        this.financeiroCampanha = financeiroCampanha;
        this.pagamento = pagamento;
    }
}
