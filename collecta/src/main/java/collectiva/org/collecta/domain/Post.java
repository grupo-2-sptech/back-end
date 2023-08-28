package collectiva.org.collecta.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    private String titulo;
    private String conteudo;
    private Date data;
}