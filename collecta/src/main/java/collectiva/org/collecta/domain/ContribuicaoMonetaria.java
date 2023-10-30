package collectiva.org.collecta.domain;

import collectiva.org.collecta.domain.enums.FormaPagamento;
import collectiva.org.collecta.domain.enums.StatusContribuicao;
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
