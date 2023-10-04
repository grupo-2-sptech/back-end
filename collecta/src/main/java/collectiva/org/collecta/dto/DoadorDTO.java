package collectiva.org.collecta.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
public class DoadorDTO {
    private UUID id;
    @NotBlank(message = "O email esta vazio")
    @Size(min = 3, message = "O email deve ter no mínimo 3 caracteres")
    private String email;

    @NotBlank(message = "A senha está vazia")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um caractere especial, um número e ter no mínimo 8 caracteres")
    private String senha;

    @NotBlank(message = "O telefone está vazio")
    @Size(min = 11, max = 11, message = "Telefone inválido")
    private String telefone;

    @NotBlank(message = "O nome esta vazio")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
    private String nome;

    @NotBlank(message = "O sobrenome esta vazio")
    @Size(min = 3, message = "O sobrenome deve ter no mínimo 3 caracteres")
    private String sobrenome;

    @NotNull(message = "A data de nascimento está vazia")
    @Past(message = "A data de nascimento deve ser no passado")
    private LocalDateTime dataNascimento;

    @NotBlank(message = "O CPF esta vazio")
    @Size(min = 11, max = 11, message  = "CPF inválido")
    private String cpf;
}
