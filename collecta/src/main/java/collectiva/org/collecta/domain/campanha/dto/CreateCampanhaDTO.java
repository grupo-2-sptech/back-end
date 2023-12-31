package collectiva.org.collecta.domain.campanha.dto;

import collectiva.org.collecta.enums.CategoriaCampanha;
import collectiva.org.collecta.enums.StatusCampanha;
import collectiva.org.collecta.enums.TipoCampanha;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CreateCampanhaDTO {
    @NotBlank(message = "O nome está vazio")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
    private String nome;

    @NotBlank(message = "A descrição está vazia")
    @Size(min = 3, message = "A descrição deve ter no mínimo 3 caracteres")
    private String descricao;

    @NotNull(message = "A data do fim está vazia")
    @Future(message = "A data de fim deve estar no futuro")
    private LocalDateTime dataFim;

    @NotNull(message = "A categoria da campanha está vazia")
    @Enumerated(EnumType.STRING)
    private CategoriaCampanha categoriaCampanha;

    @NotNull(message = "O tipo da campanha está vazio")
    @Enumerated(EnumType.STRING)
    private TipoCampanha tipoCampanha;

    @NotNull(message = "O id da organização está vazio")
    private UUID idOrganizacao;

    @NotBlank(message = "a url da imagem está vazia")
    private String urlImagem;

}
