package collectiva.org.collecta.authentication.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DoadorLoginDTO {
    @NotBlank(message = "O Email está vazio")
    private String email;

    @NotBlank(message = "A senha está vazia")
    private String senha;
}
