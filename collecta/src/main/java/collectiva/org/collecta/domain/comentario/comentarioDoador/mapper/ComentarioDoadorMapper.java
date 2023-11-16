package collectiva.org.collecta.domain.comentario.comentarioDoador.mapper;

import collectiva.org.collecta.domain.comentario.comentarioDoador.ComentarioDoador;
import collectiva.org.collecta.enums.TipoConta;
import collectiva.org.collecta.domain.comentario.comentarioDoador.dto.ComentarioDoadorDTO;

import java.time.LocalDateTime;

public class ComentarioDoadorMapper {
    private ComentarioDoadorMapper() {
    }

    public static ComentarioDoador paraEntidade(ComentarioDoadorDTO comentarioDoadorDTO){
        return ComentarioDoador.builder()
                .comentario(comentarioDoadorDTO.getComentario())
                .data(LocalDateTime.now())
                .tipoConta(TipoConta.DOADOR)
                .build();
    }
    public static ComentarioDoadorDTO paraDTO(ComentarioDoador comentarioDoador){
        return ComentarioDoadorDTO.builder()
                .id(comentarioDoador.getId())
                .comentario(comentarioDoador.getComentario())
                .data(comentarioDoador.getData())
                .tipoConta(comentarioDoador.getTipoConta())
                .build();
    }
}
