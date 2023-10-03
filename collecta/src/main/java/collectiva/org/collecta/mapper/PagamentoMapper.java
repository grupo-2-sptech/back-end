package collectiva.org.collecta.mapper;

import collectiva.org.collecta.domain.Pagamento;
import collectiva.org.collecta.dto.PagamentoDTO;

import java.time.LocalDateTime;

public class PagamentoMapper {
    private PagamentoMapper() {
    }

    public static Pagamento paraEntidade(PagamentoDTO pagamentoDTO){
        return Pagamento.builder()
                .nomeTitular(pagamentoDTO.getNomeTitular())
                .cpf(pagamentoDTO.getCpf())
                .numeroCartao(pagamentoDTO.getNumeroCartao())
                .dataValidade(pagamentoDTO.getDataValidade())
                .codigoSeguranca(pagamentoDTO.getCodigoSeguranca())
                .bandeiraCartao(pagamentoDTO.getBandeiraCartao())
                .build();
    }

    public static PagamentoDTO paraDTO(Pagamento pagamento){
        return PagamentoDTO.builder()
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
