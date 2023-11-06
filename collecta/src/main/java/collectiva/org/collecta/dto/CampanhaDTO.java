package collectiva.org.collecta.dto;

import collectiva.org.collecta.domain.enums.StatusCampanha;
import collectiva.org.collecta.domain.enums.TipoCampanha;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @NotNull(message = "O status da campanha está vazio")
    @Enumerated(EnumType.STRING)
    private StatusCampanha statusCampanha;

    @NotNull(message = "O tipo da campanha está vazio")
    @Enumerated(EnumType.STRING)
    private TipoCampanha tipoCampanha;
}
