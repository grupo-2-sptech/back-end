package collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.mapper;

import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.ContribuicaoMonetaria;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.dto.ContribuicaoMonetariaDTO;

import java.time.LocalDateTime;

public class ContribuicaoMonetariaMapper {
    private ContribuicaoMonetariaMapper() {
    }

    public static ContribuicaoMonetaria paraEntidade(ContribuicaoMonetariaDTO contribuicaoMonetariaDTO){
        return ContribuicaoMonetaria.builder()
                .nome(contribuicaoMonetariaDTO.getNome())
                .descricao(contribuicaoMonetariaDTO.getDescricao())
                .dataHora(LocalDateTime.now())
                .valor(contribuicaoMonetariaDTO.getValor())
                .parcelas(contribuicaoMonetariaDTO.getParcelas())
                .formaPagamento(contribuicaoMonetariaDTO.getFormaPagamento())
                .statusContribuicao(contribuicaoMonetariaDTO.getStatusContribuicao())
                .build();
    }

    public static ContribuicaoMonetariaDTO paraDTO(ContribuicaoMonetaria contribuicaoMonetaria){
        return ContribuicaoMonetariaDTO.builder()
                .id(contribuicaoMonetaria.getId())
                .nome(contribuicaoMonetaria.getNome())
                .descricao(contribuicaoMonetaria.getDescricao())
                .dataHora(contribuicaoMonetaria.getDataHora())
                .valor(contribuicaoMonetaria.getValor())
                .parcelas(contribuicaoMonetaria.getParcelas())
                .formaPagamento(contribuicaoMonetaria.getFormaPagamento())
                .statusContribuicao(contribuicaoMonetaria.getStatusContribuicao())
                .build();
    }
}
