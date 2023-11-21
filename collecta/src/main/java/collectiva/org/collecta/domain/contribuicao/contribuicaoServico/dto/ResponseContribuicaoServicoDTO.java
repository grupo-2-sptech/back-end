package collectiva.org.collecta.domain.contribuicao.contribuicaoServico.dto;

import collectiva.org.collecta.enums.StatusContribuicao;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ResponseContribuicaoServicoDTO {
    private UUID id;
    private String nome;
    private String descricao;
    private LocalDateTime dataHora;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFim;
    private Integer avaliacao;
    private StatusContribuicao statusContribuicao;

}
