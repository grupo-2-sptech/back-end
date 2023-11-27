package collectiva.org.collecta.domain.comentario.comentarioOrganizacao.mapper;

import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.ComentarioOrganizacao;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.dto.AssociationComentarioOrganizacaoDTO;
import collectiva.org.collecta.domain.comentario.comentarioOrganizacao.dto.ResponseComentarioOrganizacaoDTO;
import collectiva.org.collecta.domain.conta.organizacao.mapper.OrganizacaoMapper;
import collectiva.org.collecta.domain.postCampanha.mapper.PostMapper;
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
                .organizacao(OrganizacaoMapper.paraAssociacaoDTO(comentarioOrganizacao.getOrganizacao()))
                .post(PostMapper.paraAssociacaoDTO(comentarioOrganizacao.getPost()))
                .build();
    }

    public static AssociationComentarioOrganizacaoDTO paraAssociacaoDTO(ComentarioOrganizacao comentarioOrganizacao){
        return AssociationComentarioOrganizacaoDTO.builder()
                .id(comentarioOrganizacao.getId())
                .comentario(comentarioOrganizacao.getComentario())
                .data(comentarioOrganizacao.getData())
                .tipoConta(comentarioOrganizacao.getTipoConta())
                .build();
    }
}
