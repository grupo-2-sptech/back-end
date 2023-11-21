package collectiva.org.collecta.domain.comentario.comentarioDoador.mapper;

import collectiva.org.collecta.domain.comentario.comentarioDoador.ComentarioDoador;
import collectiva.org.collecta.domain.comentario.comentarioDoador.dto.ResponseComentarioDoadorDTO;
import collectiva.org.collecta.enums.TipoConta;
import collectiva.org.collecta.domain.comentario.comentarioDoador.dto.CreateComentarioDoadorDTO;

import java.time.LocalDateTime;

public class ComentarioDoadorMapper {
    private ComentarioDoadorMapper() {
    }

    public static ComentarioDoador paraEntidade(CreateComentarioDoadorDTO comentarioDoadorDTO){
        return ComentarioDoador.builder()
                .comentario(comentarioDoadorDTO.getComentario())
                .data(LocalDateTime.now())
                .tipoConta(TipoConta.DOADOR)
                .build();
    }
    public static ResponseComentarioDoadorDTO paraDTO(ComentarioDoador comentarioDoador){
        return ResponseComentarioDoadorDTO.builder()
                .id(comentarioDoador.getId())
                .comentario(comentarioDoador.getComentario())
                .data(comentarioDoador.getData())
                .tipoConta(comentarioDoador.getTipoConta())
                .build();
    }
}
