package collectiva.org.collecta.domain.conta.organizacao.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDateTime;

@Data
@Builder
public class UpdateOrganizacaoDTO {

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
    @CNPJ(message = "CNPJ inválido")
    private String cnpj;
}
