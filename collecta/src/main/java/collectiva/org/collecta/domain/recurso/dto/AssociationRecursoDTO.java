package collectiva.org.collecta.domain.recurso.dto;

import collectiva.org.collecta.enums.TipoRecurso;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AssociationRecursoDTO {
    private UUID id;
    private String nome;
    private String descricao;
    private int quantidadeArrecadada;
    private int quantidadeMeta;
    private TipoRecurso tipoRecurso;

}
