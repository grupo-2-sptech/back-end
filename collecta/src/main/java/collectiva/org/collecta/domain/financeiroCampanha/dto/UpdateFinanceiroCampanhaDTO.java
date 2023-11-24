package collectiva.org.collecta.domain.financeiroCampanha.dto;

import collectiva.org.collecta.enums.MetaStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateFinanceiroCampanhaDTO {
    @NotNull(message = "O modo de contribuição não pode ser nulo")
    @Enumerated(EnumType.STRING)
    private MetaStatus metaStatus;
}
