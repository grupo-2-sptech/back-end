package collectiva.org.collecta.domain.comentario.comentarioOrganizacao.mapper;

import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.ComentarioOrganizacao;
import collectiva.org.collecta.enums.TipoConta;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.dto.ComentarioOrganizacaoDTO;

import java.time.LocalDateTime;

public class ComentarioOrganizacaoMapper {
    private ComentarioOrganizacaoMapper() {
    }

    public static ComentarioOrganizacao paraEntidade(ComentarioOrganizacaoDTO comentarioOrganizacaoDTO){
        return ComentarioOrganizacao.builder()
                .comentario(comentarioOrganizacaoDTO.getComentario())
                .data(LocalDateTime.now())
                .tipoConta(TipoConta.ORGANIZACAO)
                .build();
    }
    public static ComentarioOrganizacaoDTO paraDTO(ComentarioOrganizacao comentarioOrganizacao){
        return ComentarioOrganizacaoDTO.builder()
                .id(comentarioOrganizacao.getId())
                .comentario(comentarioOrganizacao.getComentario())
                .data(comentarioOrganizacao.getData())
                .tipoConta(comentarioOrganizacao.getTipoConta())
                .build();
    }
}
