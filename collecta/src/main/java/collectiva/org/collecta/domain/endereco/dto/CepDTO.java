package collectiva.org.collecta.domain.endereco.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CepDTO {
    private String logradouro;
    @JsonProperty("localidade")
    private String cidade;
    @JsonProperty("uf")
    private String estado;
    private String cep;
}
