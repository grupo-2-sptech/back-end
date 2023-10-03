package collectiva.org.collecta.domain;

import collectiva.org.collecta.domain.enums.FormaPagamento;
import collectiva.org.collecta.domain.enums.StatusPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContribuicaoMonetaria extends Contribuicao{
    private BigDecimal valor;
    private Integer parcelas;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    @ManyToOne
    @JoinColumn(name="doador")
    private Doador doador;

    @ManyToOne
    @JoinColumn(name="financeiroCampanha")
    private FinanceiroCampanha financeiroCampanha;

    @Builder
    public ContribuicaoMonetaria(UUID id, String nome, String descricao, LocalDateTime dataHora, BigDecimal valor, Integer parcelas, FormaPagamento formaPagamento, StatusPagamento statusPagamento, Doador doador, FinanceiroCampanha financeiroCampanha) {
        super(id, nome, descricao, dataHora);
        this.valor = valor;
        this.parcelas = parcelas;
        this.formaPagamento = formaPagamento;
        this.statusPagamento = statusPagamento;
        this.doador = doador;
        this.financeiroCampanha = financeiroCampanha;
    }
}
