package collectiva.org.collecta.domain.contribuicao.contribuicaoServico.mapper;

import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.ContribuicaoServico;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.dto.CreateContribuicaoServicoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.dto.ResponseContribuicaoServicoDTO;
import collectiva.org.collecta.enums.StatusContribuicao;

import java.time.LocalDateTime;

public class ContribuicaoServicoMapper {
    private ContribuicaoServicoMapper() {
    }

    public static ContribuicaoServico paraEntidade(CreateContribuicaoServicoDTO contribuicaoServicoDTO){
        return ContribuicaoServico.builder()
                .nome(contribuicaoServicoDTO.getNome())
                .descricao(contribuicaoServicoDTO.getDescricao())
                .dataHora(LocalDateTime.now())
                .horaInicio(contribuicaoServicoDTO.getHoraInicio())
                .horaFim(contribuicaoServicoDTO.getHoraFim())
                .avaliacao(contribuicaoServicoDTO.getAvaliacao())
                .statusContribuicao(StatusContribuicao.EM_AGUARDO)
                .build();
    }

    public static ResponseContribuicaoServicoDTO paraDTO(ContribuicaoServico contribuicaoServico){
        return ResponseContribuicaoServicoDTO.builder()
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
