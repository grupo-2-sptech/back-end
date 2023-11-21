package collectiva.org.collecta.domain.campanha.dto;

import collectiva.org.collecta.enums.CategoriaCampanha;
import collectiva.org.collecta.enums.StatusCampanha;
import collectiva.org.collecta.enums.TipoCampanha;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ResponseCampanhaDTO {
    private UUID id;
    private String nome;
    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private CategoriaCampanha categoriaCampanha;
    private StatusCampanha statusCampanha;
    private TipoCampanha tipoCampanha;
    private int visulizacoes;

}
