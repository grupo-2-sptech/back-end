package collectiva.org.collecta.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class EnderecoDTO {
    private UUID id;

    @NotBlank(message = "O logradouro esta vazio")
    @Size(min = 4, message = "O logradouro deve ter no mínimo 4 caracteres")
    private String logradouro;

    @NotBlank(message = "A cidade esta vazia")
    @Size(min = 4, message = "A cidade deve ter no mínimo 4 caracteres")
    private String cidade;

    @NotBlank(message = "O estado esta vazio")
    @Size(min = 2, max = 2, message = "O logradouro deve ter 2 caracteres")
    private String estado;

    @NotBlank(message = "O cep esta vazio")
    @Size(min = 8, max = 8, message = "O cep deve ter 8 caracteres")
    private String cep;

    @NotNull
    @Min(value = 1, message = "O número de parcelas deve ser pelo menos 1")
    @Max(value = 999999, message = "O número de parcelas não pode exceder 999999")
    private int numero;
}
