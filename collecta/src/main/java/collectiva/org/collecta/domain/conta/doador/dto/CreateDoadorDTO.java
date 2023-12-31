package collectiva.org.collecta.domain.conta.doador.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@Builder
public class CreateDoadorDTO {
    @NotBlank(message = "O Email está vazio")
    @Email(message = "O Email está inválido")
    private String email;

    @NotBlank(message = "A senha está vazia")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um caractere especial, um número e ter no mínimo 8 caracteres")
    private String senha;

    @NotBlank(message = "O telefone está vazio")
    @Size(min = 11, max = 11, message = "Telefone inválido")
    private String telefone;

    @NotBlank(message = "O nome está vazio")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
    private String nome;

    @NotBlank(message = "O sobrenome está vazio")
    @Size(min = 3, message = "O sobrenome deve ter no mínimo 3 caracteres")
    private String sobrenome;

    @NotNull(message = "A data de nascimento está vazia")
    @Past(message = "A data de nascimento deve ser no passado")
    private LocalDate dataNascimento;

    @NotBlank(message = "O CPF está vazio")
    @CPF(message = "O CPF está inválido")
    private String cpf;
}
