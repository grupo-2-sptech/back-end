package collectiva.org.collecta.domain.pagamento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Builder
public class CreatePagamentoDTO {
    @NotBlank(message = "O nome do titular está vazio")
    @Size(min = 5, message = "O nome do titular deve ter no mínimo 5 letras")
    private String nomeTitular;

    @NotBlank(message = "O nome do titular está vazio")
    @CPF(message = "CPF deve conter exatamente 11 dígitos numéricos")
    private String cpf;

    @NotBlank(message = "O número do cartão está vazio")
    @Pattern(regexp = "\\d{16}", message = "Número do cartão deve conter exatamente 16 dígitos numéricos")
    private String numeroCartao;

    @NotBlank(message = "A data de validade está vazia")
    @Pattern(regexp = "\\d{2}-\\d{2}", message = "Data de validade deve estar no formato 'MM-YY'")
    private String dataValidade;

    @NotBlank(message = "O código de segurança está vazio")
    @Pattern(regexp = "\\d{3}", message = "Código de segurança deve conter exatamente 3 dígitos numéricos")
    private String codigoSeguranca;

    @NotBlank(message = "A bandeira do cartão esta vazia")
    private String bandeiraCartao;

}
