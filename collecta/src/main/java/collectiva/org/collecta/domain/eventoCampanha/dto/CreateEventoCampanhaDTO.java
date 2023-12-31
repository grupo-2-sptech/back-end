package collectiva.org.collecta.domain.eventoCampanha.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CreateEventoCampanhaDTO {
    @NotBlank(message = "O nome está vazio")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
    private String nome;

    @NotBlank(message = "A descrição está vazia")
    @Size(min = 3, message = "A descrição deve ter no mínimo 3 caracteres")
    private String descricao;

    @NotNull(message = "A data e hora está vazia")
    @Future(message = "A data e hora deve estar no futuro")
    private LocalDateTime dataHora;

    @NotNull(message = "A quantidade de vagas está vazia")
    @Positive(message = "O numero de vagas deve ser positivo")
    private Integer qtdVaga;

    @NotNull(message = "A url da imagem está vazia")
    private String urlImagem;

    @NotNull(message = "O local está vazio")
    private String local;

    @NotNull(message = "O id da campanha está vazio")
    private UUID idCampanha;
}
