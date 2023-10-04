package collectiva.org.collecta.mapper;

import collectiva.org.collecta.domain.ContribuicaoServico;
import collectiva.org.collecta.dto.ContribuicaoServicoDTO;

import java.time.LocalDateTime;

public class ContribuicaoServicoMapper {
    private ContribuicaoServicoMapper() {
    }

    public static ContribuicaoServico paraEntidade(ContribuicaoServicoDTO contribuicaoServicoDTO){
        return ContribuicaoServico.builder()
                .nome(contribuicaoServicoDTO.getNome())
                .descricao(contribuicaoServicoDTO.getDescricao())
                .dataHora(LocalDateTime.now())
                .horaInicio(contribuicaoServicoDTO.getHoraInicio())
                .horaFim(contribuicaoServicoDTO.getHoraFim())
                .avaliacao(contribuicaoServicoDTO.getAvaliacao())
                .statusContribuicao(contribuicaoServicoDTO.getStatusContribuicao())
                .build();
    }

    public static ContribuicaoServicoDTO paraDTO(ContribuicaoServico contribuicaoServico){
        return ContribuicaoServicoDTO.builder()
                .id(contribuicaoServico.getId())
                .nome(contribuicaoServico.getNome())
                .descricao(contribuicaoServico.getDescricao())
                .dataHora(contribuicaoServico.getDataHora())
                .horaInicio(contribuicaoServico.getHoraInicio())
                .horaFim(contribuicaoServico.getHoraFim())
                .avaliacao(contribuicaoServico.getAvaliacao())
                .statusContribuicao(contribuicaoServico.getStatusContribuicao())
                .build();
    }
}
