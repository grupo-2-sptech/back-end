package collectiva.org.collecta.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CampanhaDTO {
    private UUID id;

    @NotBlank(message = "O nome está vazio")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
    private String nome;

    @NotBlank(message = "A descrição está vazia")
    @Size(min = 3, message = "A descrição deve ter no mínimo 3 caracteres")
    private String descricao;

    @NotNull(message = "A data de inicio está vazia")
    @FutureOrPresent(message = "A data de início deve estar no futuro ou presente")
    private LocalDateTime dataInicio;

    @NotNull(message = "A data do fim está vazia")
    @Future(message = "A data de fim deve estar no futuro")
    private LocalDateTime dataFim;

    @NotBlank(message = "O status está vazio")
    @Pattern(regexp = "^(ATIVO|INATIVO)$", message = "O status deve ser 'ATIVO' ou 'INATIVO'")
    @Size(min = 3, message = "O status deve ter no mínimo 3 caracteres")
    private String status;
}
