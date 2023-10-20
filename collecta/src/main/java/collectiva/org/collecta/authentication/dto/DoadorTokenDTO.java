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
    @NotBlank(message = "O email esta vazio")
    private String email;

    @NotBlank(message = "A senha está vazia")
    private String senha;

    @NotBlank(message = "O nome esta vazio")
    private String nome;

    @NotBlank(message = "O token esta vazio")
    private String token;
}
