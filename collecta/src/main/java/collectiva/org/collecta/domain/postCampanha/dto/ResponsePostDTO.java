package collectiva.org.collecta.domain.postCampanha.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ResponsePostDTO {
    private UUID id;
    private String titulo;
    private String conteudo;
    private LocalDateTime data;
}
