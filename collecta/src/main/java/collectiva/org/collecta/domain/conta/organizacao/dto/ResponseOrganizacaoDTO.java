package collectiva.org.collecta.domain.conta.organizacao.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ResponseOrganizacaoDTO {
    private UUID id;
    private String email;
    private String telefone;
    private String nomeSocial;
    private String nomeFantasia;
}
