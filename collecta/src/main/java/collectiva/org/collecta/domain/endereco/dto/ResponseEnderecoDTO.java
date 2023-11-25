package collectiva.org.collecta.domain.endereco.dto;


import collectiva.org.collecta.domain.eventoCampanha.dto.AssociationEventoCampanhaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEnderecoDTO {
    private UUID id;
    private String logradouro;
    private String cidade;
    private String estado;
    private String cep;
    private int numero;
    private AssociationEventoCampanhaDTO eventoCampanha;
}
