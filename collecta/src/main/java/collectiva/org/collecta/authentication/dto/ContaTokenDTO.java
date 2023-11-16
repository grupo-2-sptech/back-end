package collectiva.org.collecta.authentication.dto;

import collectiva.org.collecta.enums.TipoConta;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class ContaTokenDTO {
    private UUID id;
    @NotBlank(message = "O Email está vazio")
    private String email;

    @NotBlank(message = "O tipo da conta está vazio")
    private TipoConta tipoConta;

    @NotBlank(message = "O token está vazio")
    private String token;
}
