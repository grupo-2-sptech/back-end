package collectiva.org.collecta.mapper;

import collectiva.org.collecta.domain.Recurso;
import collectiva.org.collecta.dto.RecursoDTO;

public class RecursoMapper {
    private RecursoMapper() {
    }

    public static Recurso paraEntidade(RecursoDTO recursoDTO){
        return Recurso.builder()
                .nome(recursoDTO.getNome())
                .descricao(recursoDTO.getDescricao())
                .quantidadeArrecadada(recursoDTO.getQuantidadeArrecadada())
                .quantidadeMeta(recursoDTO.getQuantidadeMeta())
                .tipoRecurso(recursoDTO.getTipoRecurso())
                .build();
    }

    public static RecursoDTO paraDTO(Recurso recurso){
        return RecursoDTO.builder()
                .id(recurso.getId())
                .nome(recurso.getNome())
                .descricao(recurso.getDescricao())
                .quantidadeArrecadada(recurso.getQuantidadeArrecadada())
                .quantidadeMeta(recurso.getQuantidadeMeta())
                .tipoRecurso(recurso.getTipoRecurso())
                .build();
    }

}
