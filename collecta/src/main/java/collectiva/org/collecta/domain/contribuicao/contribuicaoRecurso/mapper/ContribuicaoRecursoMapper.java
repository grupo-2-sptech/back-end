package collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.mapper;

import collectiva.org.collecta.domain.conta.doador.mapper.DoadorMapper;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.ContribuicaoRecurso;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.dto.AssociationContribuicaoRecursoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.dto.CreateContribuicaoRecursoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.dto.ResponseContribuicaoRecursoDTO;
import collectiva.org.collecta.domain.recurso.mapper.RecursoMapper;
import collectiva.org.collecta.enums.StatusContribuicao;

import java.time.LocalDateTime;

public class ContribuicaoRecursoMapper {
    private ContribuicaoRecursoMapper() {
    }

    public static ContribuicaoRecurso paraEntidade(CreateContribuicaoRecursoDTO contribuicaoRecursoDTO){
        return ContribuicaoRecurso.builder()
                .dataHora(LocalDateTime.now())
                .quantidade(contribuicaoRecursoDTO.getQuantidade())
                .tipoRecurso(contribuicaoRecursoDTO.getTipoRecurso())
                .statusContribuicao(StatusContribuicao.PENDENTE)
                .build();
    }

    public static ResponseContribuicaoRecursoDTO paraDTO(ContribuicaoRecurso contribuicaoRecurso){
        return ResponseContribuicaoRecursoDTO.builder()
                .id(contribuicaoRecurso.getId())
                .dataHora(contribuicaoRecurso.getDataHora())
                .quantidade(contribuicaoRecurso.getQuantidade())
                .tipoRecurso(contribuicaoRecurso.getTipoRecurso())
                .statusContribuicao(contribuicaoRecurso.getStatusContribuicao())
                .doador(DoadorMapper.paraAssociacaoDTO(contribuicaoRecurso.getDoador()))
                .recuso(RecursoMapper.paraAssociacaoDTO(contribuicaoRecurso.getRecurso()))
                .build();
    }

    public static AssociationContribuicaoRecursoDTO paraAssociacaoDTO(ContribuicaoRecurso contribuicaoRecurso){
        return AssociationContribuicaoRecursoDTO.builder()
                .id(contribuicaoRecurso.getId())
                .dataHora(contribuicaoRecurso.getDataHora())
                .quantidade(contribuicaoRecurso.getQuantidade())
                .tipoRecurso(contribuicaoRecurso.getTipoRecurso())
                .statusContribuicao(contribuicaoRecurso.getStatusContribuicao())
                .build();
    }
}
