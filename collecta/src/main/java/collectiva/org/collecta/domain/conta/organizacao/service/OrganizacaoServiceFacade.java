package collectiva.org.collecta.domain.conta.organizacao.service;


import collectiva.org.collecta.domain.conta.organizacao.Organizacao;
import collectiva.org.collecta.domain.conta.organizacao.dto.AssociationOrganizacaoDTO;
import collectiva.org.collecta.domain.conta.organizacao.dto.ResponseOrganizacaoDTO;
import collectiva.org.collecta.domain.conta.organizacao.dto.UpdateOrganizacaoDTO;
import collectiva.org.collecta.domain.conta.organizacao.mapper.OrganizacaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrganizacaoServiceFacade {
    private final OrganizacaoService organizacaoService;

    public List<ResponseOrganizacaoDTO> buscarTodasOrganizacaoes() {
        return organizacaoService.buscarTodasOrganizacoes().stream().map
                (OrganizacaoMapper::paraDTO).toList();
    }

    public ResponseOrganizacaoDTO buscarOrganizacaoPorId(UUID id) {
        return OrganizacaoMapper.paraDTO(organizacaoService.buscarOrganizacaoPorId(id));
    }

    public AssociationOrganizacaoDTO atualizarOrganizacao(UUID idOrganizacao, UpdateOrganizacaoDTO organizacaoDTO) {
        Organizacao organizacao = OrganizacaoMapper.paraEntidadeUpdate(organizacaoDTO);
        return OrganizacaoMapper.paraAssociacaoDTO(organizacaoService.atualizarOrganizacao(idOrganizacao, organizacao));
    }

    public void deletarOrganizacao(UUID id) {
        organizacaoService.deletarOrganizacao(id);
    }

}
