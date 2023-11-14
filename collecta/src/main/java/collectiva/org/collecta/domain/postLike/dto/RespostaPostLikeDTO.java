package collectiva.org.collecta.domain.postLike.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RespostaPostLikeDTO {
    private UUID id;
    private String email;
    private UUID postId;
    private String postTitulo;
}
