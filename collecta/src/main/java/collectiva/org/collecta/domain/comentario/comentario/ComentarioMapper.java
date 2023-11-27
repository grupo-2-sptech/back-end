package collectiva.org.collecta.domain.comentario.comentario;

import collectiva.org.collecta.domain.comentario.Comentario;

public class ComentarioMapper {
    private ComentarioMapper() {
    }
    public static AssociationComentarioDTO paraAssociacaoDTO(Comentario comentarioOrganizacao){
        return AssociationComentarioDTO.builder()
                .id(comentarioOrganizacao.getId())
                .comentario(comentarioOrganizacao.getComentario())
                .data(comentarioOrganizacao.getData())
                .tipoConta(comentarioOrganizacao.getTipoConta())
                .build();
    }
}
