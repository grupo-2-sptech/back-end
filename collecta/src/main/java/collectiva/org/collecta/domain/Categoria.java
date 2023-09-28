package collectiva.org.collecta.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;
    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "categoria")
    private List<Campanha> campanhas = new ArrayList<>();
}
