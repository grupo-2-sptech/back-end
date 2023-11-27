package collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria;

import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.contribuicao.Contribuicao;
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

    private String txid;

    private String idCodigoPix;


    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @ManyToOne
    private Doador doador;

    @ManyToOne
    private FinanceiroCampanha financeiroCampanha;

    @OneToOne(mappedBy = "contribuicaoMonetaria")
    private Pagamento pagamento;

    @Builder
    public ContribuicaoMonetaria(UUID id, LocalDateTime dataHora, StatusContribuicao statusContribuicao, BigDecimal valor, Integer parcelas, FormaPagamento formaPagamento, Doador doador, FinanceiroCampanha financeiroCampanha, Pagamento pagamento, String txid, String idCodigoPix) {
        super(id, dataHora, statusContribuicao);
        this.valor = valor;
        this.parcelas = parcelas;
        this.formaPagamento = formaPagamento;
        this.doador = doador;
        this.financeiroCampanha = financeiroCampanha;
        this.pagamento = pagamento;
        this.txid = txid;
        this.idCodigoPix = idCodigoPix;
    }
}
