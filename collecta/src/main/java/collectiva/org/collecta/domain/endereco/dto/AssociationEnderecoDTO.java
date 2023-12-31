package collectiva.org.collecta.domain.endereco.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssociationEnderecoDTO {
    private UUID id;
    private String logradouro;
    private String cidade;
    private String estado;
    private String cep;
    private int numero;
}
