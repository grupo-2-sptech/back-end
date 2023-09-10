package collectiva.org.collecta.mapper;

import collectiva.org.collecta.domain.Doacao;
import collectiva.org.collecta.domain.enums.ModoContribuição;
import collectiva.org.collecta.dto.DoacaoDTO;

import java.time.LocalDateTime;

public class DoacaoMapper {
    private DoacaoMapper() {
    }
    public static Doacao paraEntidade(DoacaoDTO doacaoDTO){
        return Doacao.builder()
                .valor(doacaoDTO.getValor())
                .dataHora(LocalDateTime.now())
                .modoContribuicao(ModoContribuição.valueOf(doacaoDTO.getModoContribuicao()))
                .build();
    }

    public static DoacaoDTO paraDTO(Doacao doacao){
        return DoacaoDTO.builder()
                .id(doacao.getId())
                .valor(doacao.getValor())
                .dataHora(doacao.getDataHora())
                .modoContribuicao(String.valueOf(doacao.getModoContribuicao()))
                .build();
    }
}
