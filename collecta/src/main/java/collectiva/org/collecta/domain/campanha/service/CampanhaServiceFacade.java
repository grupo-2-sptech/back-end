package collectiva.org.collecta.domain.campanha.service;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.campanha.dto.AssociationCampanhaDTO;
import collectiva.org.collecta.domain.campanha.dto.CreateCampanhaDTO;
import collectiva.org.collecta.domain.campanha.dto.ResponseCampanhaDTO;
import collectiva.org.collecta.domain.campanha.dto.UpdateCampanhaDTO;
import collectiva.org.collecta.domain.campanha.mapper.CampanhaMapper;
import collectiva.org.collecta.domain.conta.organizacao.Organizacao;
import collectiva.org.collecta.domain.conta.organizacao.service.OrganizacaoService;
import collectiva.org.collecta.enums.CategoriaCampanha;
import collectiva.org.collecta.enums.TipoCampanha;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CampanhaServiceFacade {
    private final CampanhaService campanhaService;
    private final OrganizacaoService organizacaoService;

    public List<ResponseCampanhaDTO> buscarTodasCampanhas() {
        return campanhaService.buscarTodasCampanhas().stream().map
                (CampanhaMapper::paraDTO).toList();
    }

    public List<ResponseCampanhaDTO> buscarTop3CampanhasPorTipo(TipoCampanha tipoCampanha) {
        return campanhaService.buscarTop3CampanhasPorTipo(tipoCampanha).stream().map
                (CampanhaMapper::paraDTO).toList();
    }

    public List<ResponseCampanhaDTO> buscarCampanhasPorGenero(CategoriaCampanha categoriaCampanha) {
        return campanhaService.buscarCampanhasPorGenero(categoriaCampanha).stream().map
                (CampanhaMapper::paraDTO).toList();
    }

    public ResponseCampanhaDTO buscarCampanhaPorId(UUID id) {
        return CampanhaMapper.paraDTO(campanhaService.buscarCampanhaPorId(id));
    }

    public AssociationCampanhaDTO criarCampanha(UUID idOrganizacao, CreateCampanhaDTO campanhaDTO) {
        Organizacao organizacao = organizacaoService.buscarOrganizacaoPorId(idOrganizacao);
        Campanha campanha = CampanhaMapper.paraEntidade(campanhaDTO);
        return CampanhaMapper.paraAssociacaoDTO(campanhaService.criarCampanha(campanha, organizacao));
    }

    public AssociationCampanhaDTO atualizarCampanha(UUID idCampanha, UpdateCampanhaDTO campanhaDTO) {
        Campanha campanha = CampanhaMapper.paraEntidadeUpdate(campanhaDTO);
        return CampanhaMapper.paraAssociacaoDTO(campanhaService.atualizarCampanha(idCampanha, campanha));
    }

    public void deletarCampanha(UUID id) {
        campanhaService.deletarCampanha(id);
    }

}
