package collectiva.org.collecta.domain;

import collectiva.org.collecta.domain.enums.MetaStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FinanceiroCampanha {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private BigDecimal valorMeta;
    private BigDecimal valorAtingido;

    @Enumerated(EnumType.STRING)
    private MetaStatus metaStatus;

    @OneToMany(mappedBy = "financeiroCampanha")
    private List<ContribuicaoMonetaria> contribuicaoMonetarias = new ArrayList();

    @ManyToOne
    @JoinColumn(name = "campanha")
    private Campanha campanha;

}
