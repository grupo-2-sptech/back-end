package collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.mapper;

import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.ContribuicaoMonetaria;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.dto.CreateContribuicaoMonetariaDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.dto.ResponseContribuicaoMonetariaDTO;

import java.time.LocalDateTime;

public class ContribuicaoMonetariaMapper {
    private ContribuicaoMonetariaMapper() {
    }

    public static ContribuicaoMonetaria paraEntidade(CreateContribuicaoMonetariaDTO contribuicaoMonetariaDTO){
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

    public static ResponseContribuicaoMonetariaDTO paraDTO(ContribuicaoMonetaria contribuicaoMonetaria){
        return ResponseContribuicaoMonetariaDTO.builder()
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
