package collectiva.org.collecta.domain;

import collectiva.org.collecta.domain.enums.StatusContribuicao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Contribuicao {
    @Id
    @GeneratedValue(generator = "uuid1")
    private UUID id;
    private String nome;
    private String descricao;
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private StatusContribuicao statusContribuicao;

}
