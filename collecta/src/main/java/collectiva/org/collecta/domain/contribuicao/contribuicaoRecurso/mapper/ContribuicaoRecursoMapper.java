package collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.mapper;

import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.ContribuicaoRecurso;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.dto.ContribuicaoRecursoDTO;

import java.time.LocalDateTime;

public class ContribuicaoRecursoMapper {
    private ContribuicaoRecursoMapper() {
    }

    public static ContribuicaoRecurso paraEntidade(ContribuicaoRecursoDTO contribuicaoRecursoDTO){
        return ContribuicaoRecurso.builder()
                .nome(contribuicaoRecursoDTO.getNome())
                .descricao(contribuicaoRecursoDTO.getDescricao())
                .dataHora(LocalDateTime.now())
                .quantidade(contribuicaoRecursoDTO.getQuantidade())
                .tipoRecurso(contribuicaoRecursoDTO.getTipoRecurso())
                .statusContribuicao(contribuicaoRecursoDTO.getStatusContribuicao())
                .build();
    }

    public static ContribuicaoRecursoDTO paraDTO(ContribuicaoRecurso contribuicaoRecurso){
        return ContribuicaoRecursoDTO.builder()
                .id(contribuicaoRecurso.getId())
                .nome(contribuicaoRecurso.getNome())
                .descricao(contribuicaoRecurso.getDescricao())
                .dataHora(contribuicaoRecurso.getDataHora())
                .quantidade(contribuicaoRecurso.getQuantidade())
                .tipoRecurso(contribuicaoRecurso.getTipoRecurso())
                .statusContribuicao(contribuicaoRecurso.getStatusContribuicao())
                .build();
    }
}
