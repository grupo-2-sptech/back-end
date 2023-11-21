package collectiva.org.collecta.domain.pagamento.mapper;

import collectiva.org.collecta.domain.pagamento.Pagamento;
import collectiva.org.collecta.domain.pagamento.dto.CreatePagamentoDTO;
import collectiva.org.collecta.domain.pagamento.dto.ResponsePagamentoDTO;

public class PagamentoMapper {
    private PagamentoMapper() {
    }

    public static Pagamento paraEntidade(CreatePagamentoDTO pagamentoDTO){
        return Pagamento.builder()
                .nomeTitular(pagamentoDTO.getNomeTitular())
                .cpf(pagamentoDTO.getCpf())
                .numeroCartao(pagamentoDTO.getNumeroCartao())
                .dataValidade(pagamentoDTO.getDataValidade())
                .codigoSeguranca(pagamentoDTO.getCodigoSeguranca())
                .bandeiraCartao(pagamentoDTO.getBandeiraCartao())
                .build();
    }

    public static ResponsePagamentoDTO paraDTO(Pagamento pagamento){
        return ResponsePagamentoDTO.builder()
                .id(pagamento.getId())
                .nomeTitular(pagamento.getNomeTitular())
                .cpf(pagamento.getCpf())
                .numeroCartao(pagamento.getNumeroCartao())
                .dataValidade(pagamento.getDataValidade())
                .codigoSeguranca(pagamento.getCodigoSeguranca())
                .bandeiraCartao(pagamento.getBandeiraCartao())
                .build();
    }
}
