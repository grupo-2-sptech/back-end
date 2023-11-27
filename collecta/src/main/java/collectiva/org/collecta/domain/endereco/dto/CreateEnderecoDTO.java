package collectiva.org.collecta.domain.endereco.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEnderecoDTO {
    @NotBlank(message = "O logradouro está vazio")
    @Size(min = 4, message = "O logradouro deve ter no mínimo 4 caracteres")
    private String logradouro;

    @JsonProperty("localidade")
    @NotBlank(message = "A cidade está vazia")
    @Size(min = 4, message = "A cidade deve ter no mínimo 4 caracteres")
    private String cidade;

    @JsonProperty("uf")
    @NotBlank(message = "O estado está vazio")
    @Size(min = 2, max = 2, message = "O logradouro deve ter 2 caracteres")
    private String estado;

    @NotBlank(message = "O cep está vazio")
    @Size(min = 8, max = 8, message = "O cep deve ter 8 caracteres")
    private String cep;

    @NotNull
    @Min(value = 1, message = "O número de parcelas deve ser pelo menos 1")
    @Max(value = 999999, message = "O número de parcelas não pode exceder 999999")
    private int numero;

    @NotNull(message = "O id do evento está vazio")
    private UUID idEventoCampanha;
}
