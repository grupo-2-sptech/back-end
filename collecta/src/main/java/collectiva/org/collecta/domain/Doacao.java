package collectiva.org.collecta.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Doacao {
    @Id
    @GeneratedValue( generator = "uuid")
    private UUID id;
    private BigDecimal valor;
    private LocalDateTime dataHora;
    private String modoContribuicao;

}
