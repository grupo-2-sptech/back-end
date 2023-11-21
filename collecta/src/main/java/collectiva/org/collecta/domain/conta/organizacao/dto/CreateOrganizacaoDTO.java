package collectiva.org.collecta.domain.conta.organizacao.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CreateOrganizacaoDTO {
    @NotBlank(message = "O Email está vazio")
    @Size(min = 3, message = "O email deve ter no mínimo 3 caracteres")
    private String email;


    @NotBlank(message = "A senha está vazia")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um caractere especial, um número e ter no mínimo 8 caracteres")
    private String senha;

    @NotBlank(message = "O telefone está vazio")
    @Size(min = 11, max = 11, message = "Telefone inválido")
    private String telefone;

    @NotBlank(message = "O nome social está vazio")
    @Size(min = 3, message = "O nome social deve ter no mínimo 3 caracteres")
    private String nomeSocial;

    @NotBlank(message = "O nome fantasia está vazio")
    @Size(min = 3, message = "O nome fantasia deve ter no mínimo 3 caracteres")
    private String nomeFantasia;

    @NotNull(message = "A data de fundação está vazia")
    @Past(message = "A data de fundação deve ser no passado")
    private LocalDateTime dataFundacao;

    @NotNull(message = "O CNPJ está vazio")
    @Size(min = 11, max = 11, message = "CNPJ inválido")
    private String cnpj;
}
