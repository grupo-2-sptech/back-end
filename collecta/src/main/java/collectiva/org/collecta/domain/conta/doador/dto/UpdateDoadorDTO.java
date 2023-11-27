package collectiva.org.collecta.domain.conta.doador.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@Builder
public class UpdateDoadorDTO {

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
