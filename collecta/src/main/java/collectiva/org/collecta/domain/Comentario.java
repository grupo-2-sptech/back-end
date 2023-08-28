package collectiva.org.collecta.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Comentario {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    private String comentario;
    private Date data;
}