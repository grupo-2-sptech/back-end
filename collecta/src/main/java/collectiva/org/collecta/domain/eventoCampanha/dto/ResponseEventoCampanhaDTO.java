package collectiva.org.collecta.domain.eventoCampanha.dto;

import collectiva.org.collecta.domain.campanha.dto.AssociationCampanhaDTO;
import collectiva.org.collecta.domain.endereco.dto.AssociationEnderecoDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ResponseEventoCampanhaDTO {
    private UUID id;
    private String nome;
    private String descricao;
    private Integer qtdVaga;
    private String urlImagem;
    private String local;
    private LocalDateTime dataHora;
    private AssociationCampanhaDTO campanha;
    private List<AssociationEnderecoDTO> enderecos;
}
