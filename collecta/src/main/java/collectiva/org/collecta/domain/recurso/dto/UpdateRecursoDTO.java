package collectiva.org.collecta.domain.recurso.dto;

import collectiva.org.collecta.enums.TipoRecurso;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateRecursoDTO {
    @NotBlank(message = "O nome está vazio")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
    private String nome;

    @NotBlank(message = "A descrição está vazio")
    @Size(min = 5, message = "A descrição deve ter no mínimo 5 caracteres")
    private String descricao;

    @NotNull
    @Min(value = 1, message = "A quantidade arrecadada deve ser pelo menos 1")
    @Max(value = 99999999, message = "A quantidade arrecadada não pode exceder 99999999")
    private int quantidadeArrecadada;

    @Min(value = 1, message = "A meta deve ser pelo menos 1")
    @Max(value = 99999999, message = "A meta não pode exceder 99999999")
    private int quantidadeMeta;

    @NotNull(message = "A forma de pagamento está vazio")
    @Enumerated(EnumType.STRING)
    private TipoRecurso tipoRecurso;
}
