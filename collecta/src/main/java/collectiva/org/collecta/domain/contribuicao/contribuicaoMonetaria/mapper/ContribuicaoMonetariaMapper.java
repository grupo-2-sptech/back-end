package collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.mapper;

import collectiva.org.collecta.domain.conta.doador.mapper.DoadorMapper;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.ContribuicaoMonetaria;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.dto.AssociationContribuicaoMonetariaDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.dto.CreateContribuicaoMonetariaDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.dto.ResponseContribuicaoMonetariaDTO;
import collectiva.org.collecta.domain.financeiroCampanha.mapper.FinanceiroCampanhaMapper;
import collectiva.org.collecta.domain.pagamento.mapper.PagamentoMapper;
import collectiva.org.collecta.enums.StatusContribuicao;

import java.time.LocalDateTime;
import java.util.Optional;

public class ContribuicaoMonetariaMapper {
    private ContribuicaoMonetariaMapper() {
    }

    public static ContribuicaoMonetaria paraEntidade(CreateContribuicaoMonetariaDTO contribuicaoMonetariaDTO, String idCodigoPix, String txid){
        return ContribuicaoMonetaria.builder()
                .dataHora(LocalDateTime.now())
                .valor(contribuicaoMonetariaDTO.getValor())
                .parcelas(contribuicaoMonetariaDTO.getParcelas())
                .formaPagamento(contribuicaoMonetariaDTO.getFormaPagamento())
                .statusContribuicao(StatusContribuicao.PROCESSANDO)
                .txid(txid)
                .idCodigoPix(idCodigoPix)
                .build();
    }

    public static ResponseContribuicaoMonetariaDTO paraDTO(ContribuicaoMonetaria contribuicaoMonetaria){
        return ResponseContribuicaoMonetariaDTO.builder()
                .id(contribuicaoMonetaria.getId())
                .dataHora(contribuicaoMonetaria.getDataHora())
                .valor(contribuicaoMonetaria.getValor())
                .parcelas(contribuicaoMonetaria.getParcelas())
                .formaPagamento(contribuicaoMonetaria.getFormaPagamento())
                .statusContribuicao(contribuicaoMonetaria.getStatusContribuicao())
                .doador(DoadorMapper.paraAssociacaoDTO(contribuicaoMonetaria.getDoador()))
                .financeiroCampanha(FinanceiroCampanhaMapper.paraAssociacaoDTO(contribuicaoMonetaria.getFinanceiroCampanha()))
                .pagamento(Optional.ofNullable(contribuicaoMonetaria.getPagamento()).map(PagamentoMapper::paraAssociacaoDTO).orElse(null))
                .idCodigoPix(contribuicaoMonetaria.getIdCodigoPix())
                .txid(contribuicaoMonetaria.getTxid())
                .build();
    }

    public static AssociationContribuicaoMonetariaDTO paraAssociacaoDTO(ContribuicaoMonetaria contribuicaoMonetaria){
        return AssociationContribuicaoMonetariaDTO.builder()
                .id(contribuicaoMonetaria.getId())
                .dataHora(contribuicaoMonetaria.getDataHora())
                .valor(contribuicaoMonetaria.getValor())
                .parcelas(contribuicaoMonetaria.getParcelas())
                .formaPagamento(contribuicaoMonetaria.getFormaPagamento())
                .statusContribuicao(contribuicaoMonetaria.getStatusContribuicao())
                .txid(contribuicaoMonetaria.getTxid())
                .idCodigoPix(contribuicaoMonetaria.getIdCodigoPix())
                .build();
    }
}
