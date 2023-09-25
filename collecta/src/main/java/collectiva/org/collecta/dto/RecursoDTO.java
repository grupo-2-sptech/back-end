package collectiva.org.collecta.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class RecursoDTO {
    private UUID id;
    @NotBlank(message = "O nome esta vazio")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
    private String nome;

    @NotBlank(message = "A descrição esta vazio")
    @Size(min = 5, message = "A descrição deve ter no mínimo 5 caracteres")
    private String descricao;

    @NotNull
    @Min(value = 1, message = "A quantidade arrecadada deve ser pelo menos 1")
    @Max(value = 99999999, message = "A quantidade arrecadada não pode exceder 99999999")
    private int quantidadeArrecadada;

    @Min(value = 1, message = "A meta deve ser pelo menos 1")
    @Max(value = 99999999, message = "A meta não pode exceder 99999999")
    private int quantidadeMeta;

}
