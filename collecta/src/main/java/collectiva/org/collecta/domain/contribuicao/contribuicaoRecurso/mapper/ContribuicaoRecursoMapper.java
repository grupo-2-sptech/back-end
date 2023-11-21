package collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.mapper;

import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.ContribuicaoRecurso;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.dto.CreateContribuicaoRecursoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.dto.ResponseContribuicaoRecursoDTO;

import java.time.LocalDateTime;

public class ContribuicaoRecursoMapper {
    private ContribuicaoRecursoMapper() {
    }

    public static ContribuicaoRecurso paraEntidade(CreateContribuicaoRecursoDTO contribuicaoRecursoDTO){
        return ContribuicaoRecurso.builder()
                .nome(contribuicaoRecursoDTO.getNome())
                .descricao(contribuicaoRecursoDTO.getDescricao())
                .dataHora(LocalDateTime.now())
                .quantidade(contribuicaoRecursoDTO.getQuantidade())
                .tipoRecurso(contribuicaoRecursoDTO.getTipoRecurso())
                .statusContribuicao(contribuicaoRecursoDTO.getStatusContribuicao())
                .build();
    }

    public static ResponseContribuicaoRecursoDTO paraDTO(ContribuicaoRecurso contribuicaoRecurso){
        return ResponseContribuicaoRecursoDTO.builder()
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
