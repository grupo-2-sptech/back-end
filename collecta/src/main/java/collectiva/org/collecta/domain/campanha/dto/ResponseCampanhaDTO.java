package collectiva.org.collecta.domain.campanha.dto;

import collectiva.org.collecta.domain.conta.organizacao.dto.AssociationOrganizacaoDTO;
import collectiva.org.collecta.domain.eventoCampanha.dto.AssociationEventoCampanhaDTO;
import collectiva.org.collecta.domain.financeiroCampanha.dto.AssociationFinanceiroCampanhaDTO;
import collectiva.org.collecta.domain.recurso.dto.AssociationRecursoDTO;
import collectiva.org.collecta.enums.CategoriaCampanha;
import collectiva.org.collecta.enums.StatusCampanha;
import collectiva.org.collecta.enums.TipoCampanha;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ResponseCampanhaDTO {
    private UUID id;
    private String nome;
    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private CategoriaCampanha categoriaCampanha;
    private StatusCampanha statusCampanha;
    private TipoCampanha tipoCampanha;
    private int visulizacoes;
    private String urlImagem;

    private AssociationOrganizacaoDTO organizacao;
    private AssociationFinanceiroCampanhaDTO financeiroCampanha;
    private List<AssociationRecursoDTO> recursos;
    private List<AssociationEventoCampanhaDTO> eventosCampanha;


}
