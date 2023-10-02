package collectiva.org.collecta.mapper;

import collectiva.org.collecta.domain.Pagamento;
import collectiva.org.collecta.dto.PagamentoDTO;

import java.time.LocalDateTime;

public class PagamentoMapper {
    private PagamentoMapper() {
    }

    public static Pagamento paraEntidade(PagamentoDTO pagamentoDTO){
        return Pagamento.builder()
                .formaPagamento(pagamentoDTO.getFormaPagamento())
                .plano(pagamentoDTO.getPlano())
                .dataHora(LocalDateTime.now())
                .parcelas(pagamentoDTO.getParcelas())
                .valor(pagamentoDTO.getValor())
                .build();
    }

    public static PagamentoDTO paraDTO(Pagamento pagamento){
        return PagamentoDTO.builder()
                .id(pagamento.getId())
                .plano(pagamento.getPlano())
                .formaPagamento(pagamento.getFormaPagamento())
                .dataHora(pagamento.getDataHora())
                .parcelas(pagamento.getParcelas())
                .valor(pagamento.getValor())
                .build();
    }
}
