package collectiva.org.collecta.domain.financeiroCampanha.dto;

import collectiva.org.collecta.enums.MetaStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class ResponseFinanceiroCampanhaDTO {
    private UUID id;
    private BigDecimal valorAtingido;
    private BigDecimal valorMeta;
    private MetaStatus metaStatus;
}
