package collectiva.org.collecta.mapper;

import collectiva.org.collecta.domain.Pagamento;
import collectiva.org.collecta.dto.PagamentoDTO;

public class PagamentoMapper {
    private PagamentoMapper() {
    }

    public static Pagamento paraEntidade(PagamentoDTO pagamentoDTO){
        return Pagamento.builder()
                .formaPagamento(pagamentoDTO.getFormaPagamento())
                .parcelas(pagamentoDTO.getParcelas())
                .build();
    }

    public static PagamentoDTO paraDTO(Pagamento pagamento){
        return PagamentoDTO.builder()
                .id(pagamento.getId())
                .formaPagamento(pagamento.getFormaPagamento())
                .parcelas(pagamento.getParcelas())
                .build();
    }
}
