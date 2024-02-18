package collectiva.org.collecta.domain.campanha.service;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.campanha.dto.AssociationCampanhaDTO;
import collectiva.org.collecta.domain.campanha.dto.CreateCampanhaDTO;
import collectiva.org.collecta.domain.campanha.dto.ResponseCampanhaDTO;
import collectiva.org.collecta.domain.campanha.dto.UpdateCampanhaDTO;
import collectiva.org.collecta.domain.campanha.mapper.CampanhaMapper;
import collectiva.org.collecta.domain.campanha.repository.CampanhaRepository;
import collectiva.org.collecta.domain.conta.organizacao.Organizacao;
import collectiva.org.collecta.domain.conta.organizacao.service.OrganizacaoService;
import collectiva.org.collecta.enums.CategoriaCampanha;
import collectiva.org.collecta.enums.TipoCampanha;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CampanhaService {
    private final CampanhaRepository campanhaRepository;
    private final OrganizacaoService organizacaoService;

    public void incrementarVisualizacao(Campanha campanha) {
        campanha.setVisualizacoes(campanha.getVisualizacoes() + 1);
        campanhaRepository.save(campanha);
    }

    public Campanha buscarExisteCampanha(UUID id) {
        return campanhaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Campanha"));
    }

    public List<ResponseCampanhaDTO> buscarTodasCampanhas() {
        return campanhaRepository.findAll().stream().map
                (CampanhaMapper::paraDTO).toList();
    }

    public List<ResponseCampanhaDTO> buscarTop3CampanhasPorTipo(TipoCampanha tipoCampanha) {
        return campanhaRepository.findTop3ByTipoCampanhaOrderByVisualizacoesDesc(tipoCampanha).stream().map
                (CampanhaMapper::paraDTO).toList();
    }

    public List<ResponseCampanhaDTO> buscarCampanhasPorGenero(CategoriaCampanha categoriaCampanha) {
        return campanhaRepository.findByCategoriaCampanha(categoriaCampanha).stream().map
                (CampanhaMapper::paraDTO).toList();
    }

    public ResponseCampanhaDTO buscarCampanhaPorId(UUID id) {
        return CampanhaMapper.paraDTO(buscarExisteCampanha(id));
    }

    public AssociationCampanhaDTO criarCampanha(CreateCampanhaDTO campanhaDTO, UUID idCampanha) {
        Organizacao organizacao = organizacaoService.buscarOrganizacaoPorId(idCampanha);
        Campanha campanha = CampanhaMapper.paraEntidade(campanhaDTO);
        campanha.setOrganizacao(organizacao);
        return CampanhaMapper.paraAssociacaoDTO(campanhaRepository.save(campanha));
    }

    public AssociationCampanhaDTO atualizarCampanha(UUID idCampanha, UpdateCampanhaDTO campanhaDTO) {
        Campanha campanha = CampanhaMapper.paraEntidadeUpdate(campanhaDTO);
        Campanha novaCampanha = buscarExisteCampanha(idCampanha);
        novaCampanha.setNome(campanha.getNome());
        novaCampanha.setDescricao(campanha.getDescricao());
        novaCampanha.setDataInicio(campanha.getDataInicio());
        novaCampanha.setDataFim(campanha.getDataFim());
        novaCampanha.setCategoriaCampanha(campanha.getCategoriaCampanha());
        novaCampanha.setTipoCampanha(campanha.getTipoCampanha());
        novaCampanha.setStatusCampanha(campanha.getStatusCampanha());
        return CampanhaMapper.paraAssociacaoDTO(campanhaRepository.save(novaCampanha));
    }

    public void deletarCampanha(UUID id) {
        buscarExisteCampanha(id);
        campanhaRepository.deleteById(id);
    }
}

