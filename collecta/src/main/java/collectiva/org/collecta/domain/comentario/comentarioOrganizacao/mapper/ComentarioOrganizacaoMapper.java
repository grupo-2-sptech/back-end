package collectiva.org.collecta.domain.comentario.comentarioOrganizacao.mapper;

import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.ComentarioOrganizacao;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.dto.ResponseComentarioOrganizacaoDTO;
import collectiva.org.collecta.enums.TipoConta;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.dto.CreateComentarioOrganizacaoDTO;

import java.time.LocalDateTime;

public class ComentarioOrganizacaoMapper {
    private ComentarioOrganizacaoMapper() {
    }

    public static ComentarioOrganizacao paraEntidade(CreateComentarioOrganizacaoDTO comentarioOrganizacaoDTO){
        return ComentarioOrganizacao.builder()
                .comentario(comentarioOrganizacaoDTO.getComentario())
                .data(LocalDateTime.now())
                .tipoConta(TipoConta.ORGANIZACAO)
                .build();
    }
    public static ResponseComentarioOrganizacaoDTO paraDTO(ComentarioOrganizacao comentarioOrganizacao){
        return ResponseComentarioOrganizacaoDTO.builder()
                .id(comentarioOrganizacao.getId())
                .comentario(comentarioOrganizacao.getComentario())
                .data(comentarioOrganizacao.getData())
                .tipoConta(comentarioOrganizacao.getTipoConta())
                .build();
    }
}
