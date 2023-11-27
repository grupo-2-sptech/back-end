package collectiva.org.collecta.domain.recurso.mapper;

import collectiva.org.collecta.domain.campanha.mapper.CampanhaMapper;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.mapper.ContribuicaoRecursoMapper;
import collectiva.org.collecta.domain.recurso.Recurso;
import collectiva.org.collecta.domain.recurso.dto.AssociationRecursoDTO;
import collectiva.org.collecta.domain.recurso.dto.CreateRecursoDTO;
import collectiva.org.collecta.domain.recurso.dto.ResponseRecursoDTO;
import collectiva.org.collecta.domain.recurso.dto.UpdateRecursoDTO;

public class RecursoMapper {
    private RecursoMapper() {
    }

    public static Recurso paraEntidade(CreateRecursoDTO recursoDTO){
        return Recurso.builder()
                .nome(recursoDTO.getNome())
                .descricao(recursoDTO.getDescricao())
                .quantidadeArrecadada(recursoDTO.getQuantidadeArrecadada())
                .quantidadeMeta(recursoDTO.getQuantidadeMeta())
                .tipoRecurso(recursoDTO.getTipoRecurso())
                .build();
    }

    public static Recurso paraEntidadeUpdate(UpdateRecursoDTO recursoDTO){
        return Recurso.builder()
                .nome(recursoDTO.getNome())
                .descricao(recursoDTO.getDescricao())
                .quantidadeArrecadada(recursoDTO.getQuantidadeArrecadada())
                .quantidadeMeta(recursoDTO.getQuantidadeMeta())
                .tipoRecurso(recursoDTO.getTipoRecurso())
                .build();
    }

    public static ResponseRecursoDTO paraDTO(Recurso recurso){
        return ResponseRecursoDTO.builder()
                .id(recurso.getId())
                .nome(recurso.getNome())
                .descricao(recurso.getDescricao())
                .quantidadeArrecadada(recurso.getQuantidadeArrecadada())
                .quantidadeMeta(recurso.getQuantidadeMeta())
                .tipoRecurso(recurso.getTipoRecurso())
                .campanha(CampanhaMapper.paraAssociacaoDTO(recurso.getCampanha()))
                .recursosDoados(recurso.getContribuicaoRecursos().stream().map(ContribuicaoRecursoMapper::paraAssociacaoDTO).toList())
                .build();
    }

    public static AssociationRecursoDTO paraAssociacaoDTO(Recurso recurso){
        return AssociationRecursoDTO.builder()
                .id(recurso.getId())
                .nome(recurso.getNome())
                .descricao(recurso.getDescricao())
                .quantidadeArrecadada(recurso.getQuantidadeArrecadada())
                .quantidadeMeta(recurso.getQuantidadeMeta())
                .tipoRecurso(recurso.getTipoRecurso())
                .build();
    }

}
