package collectiva.org.collecta.dto;

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
public class CampanhaDTO {
    private UUID id;

    @NotBlank(message = "O nome esta vazio")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
    private String nome;

    @NotBlank(message = "A descrição esta vazia")
    @Size(min = 3, message = "A descrição deve ter no mínimo 3 caracteres")
    private String descricao;

    @NotNull(message = "A data de inicio esta vazia")
    @FutureOrPresent(message = "A data de início deve estar no futuro ou presente")
    private LocalDateTime dataInicio;

    @NotNull(message = "A data do fim esta vazia")
    @Future(message = "A data de fim deve estar no futuro")
    private LocalDateTime dataFim;

    @NotBlank(message = "O status esta vazio")
    @Pattern(regexp = "^(ATIVO|INATIVO)$", message = "O status deve ser 'ATIVO' ou 'INATIVO'")
    @Size(min = 3, message = "O status deve ter no mínimo 3 caracteres")
    private String status;
}
