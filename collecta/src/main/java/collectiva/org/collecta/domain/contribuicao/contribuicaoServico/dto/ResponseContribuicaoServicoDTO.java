package collectiva.org.collecta.domain.contribuicao.contribuicaoServico.dto;

import collectiva.org.collecta.domain.conta.doador.dto.AssociationDoadorDTO;
import collectiva.org.collecta.domain.eventoCampanha.dto.AssociationEventoCampanhaDTO;
import collectiva.org.collecta.enums.StatusContribuicao;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ResponseContribuicaoServicoDTO {
    private UUID id;
    private LocalDateTime dataHora;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFim;
    private Integer avaliacao;
    private StatusContribuicao statusContribuicao;
    private AssociationDoadorDTO doador;
    private AssociationEventoCampanhaDTO eventoCampanha;

}
