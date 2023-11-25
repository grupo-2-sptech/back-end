package collectiva.org.collecta.domain.recurso.dto;

import collectiva.org.collecta.domain.campanha.dto.AssociationCampanhaDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.dto.AssociationContribuicaoRecursoDTO;
import collectiva.org.collecta.enums.TipoRecurso;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ResponseRecursoDTO {
    private UUID id;
    private String nome;
    private String descricao;
    private int quantidadeArrecadada;
    private int quantidadeMeta;
    private TipoRecurso tipoRecurso;
    private AssociationCampanhaDTO campanha;
    private List<AssociationContribuicaoRecursoDTO> recursosDoados;
}
