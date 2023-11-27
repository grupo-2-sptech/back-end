package collectiva.org.collecta.domain.conta.doador.dto;

import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.dto.AssociationContribuicaoMonetariaDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.dto.AssociationContribuicaoRecursoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.dto.AssociationContribuicaoServicoDTO;
import collectiva.org.collecta.domain.plano.dto.AssociationPlanoDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ResponseDoadorDTO {
    private UUID id;
    private String email;
    private String telefone;
    private String nome;
    private String sobrenome;
    private String cpf;
    private List<AssociationContribuicaoServicoDTO> servicosFeitos;
    private List<AssociationContribuicaoMonetariaDTO> doacoesMonetarias;
    private List<AssociationContribuicaoRecursoDTO> recursosDoados;
    private AssociationPlanoDTO plano;
}
