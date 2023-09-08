package collectiva.org.collecta.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CampanhaDTO {
    private UUID id;

    @NotBlank(message = "O nome esta vazio")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteresz")
    private String nome;

    @NotBlank(message = "A descrição esta vazia")
    @Size(min = 3, message = "A descrição deve ter no mínimo 3 caracteres")
    private String descricao;

    @NotNull(message = "O valor meta esta vazio")
    @DecimalMin(value = "0.01", message = "O valor da meta deve ser maior que zero")
    private BigDecimal valorMeta;

    @NotNull(message = "O valor arrecadado esta vazio")
    @DecimalMin(value = "0.1", message = "O valor arrecadado deve ser maior que zero")
    private BigDecimal valorArrecadado;

    @NotNull(message = "A data de inicio esta vazia")
    @FutureOrPresent(message = "A data de início deve estar no futuro ou presente")
    private Date dataInicio;

    @NotNull(message = "A data do fim esta vazia")
    @Future(message = "A data de fim deve estar no futuro")
    private Date dataFim;

    @NotBlank(message = "O status esta vazio")
    @Pattern(regexp = "^(ativo|inativo)$", message = "O status deve ser 'ativo' ou 'inativo'")
    @Size(min = 3, message = "O status deve ter no mínimo 3 caracteres")
    private String status;
}
