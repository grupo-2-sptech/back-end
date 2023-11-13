package collectiva.org.collecta.authentication.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class DoadorTokenDTO {
    private UUID id;
    @NotBlank(message = "O Email está vazio")
    private String email;

    @NotBlank(message = "O nome está vazio")
    private String nome;

    @NotBlank(message = "O token está vazio")
    private String token;
}
