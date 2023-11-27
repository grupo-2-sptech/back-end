package collectiva.org.collecta.domain.acaoCampanha.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class AssociationAcaoCampanhaDTO {
    private UUID id;
    private String nome;
    private String descricao;
    private LocalDate data;
    private BigDecimal valor;

}
