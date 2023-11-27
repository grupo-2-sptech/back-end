package collectiva.org.collecta.domain.conta.organizacao.dto;

import collectiva.org.collecta.domain.campanha.dto.AssociationCampanhaDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ResponseOrganizacaoDTO {
    private UUID id;
    private String email;
    private String telefone;
    private String nomeSocial;
    private String nomeFantasia;
    private List<AssociationCampanhaDTO> campanhas;
}
