package collectiva.org.collecta.dto;

import collectiva.org.collecta.domain.enums.FormaPagamento;
import collectiva.org.collecta.domain.enums.Plano;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
public class PagamentoDTO {
    private UUID id;

    @NotBlank(message = "O nome do titular esta vazio")
    @Size(min = 5, message = "O nome do titular deve ter no mínimo 5 letras")
    private String nomeTitular;

    @Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 dígitos numéricos")
    private String cpf;

    @Pattern(regexp = "\\d{16}", message = "Número do cartão deve conter exatamente 16 dígitos numéricos")
    private String numeroCartao;

    @Pattern(regexp = "\\d{2}-\\d{2}", message = "Data de validade deve estar no formato 'MM-YY'")
    private String dataValidade;

    @Pattern(regexp = "\\d{3}", message = "Código de segurança deve conter exatamente 3 dígitos numéricos")
    private String codigoSeguranca;

    @NotBlank(message = "A bandeira do cartão esta vazia")
    private String bandeiraCartao;

}
