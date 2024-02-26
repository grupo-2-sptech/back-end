package collectiva.org.collecta.domain.contribuicao.contribuicaoServico.service;


import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.conta.doador.service.DoadorService;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.ContribuicaoServico;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.dto.AssociationContribuicaoServicoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.dto.CreateContribuicaoServicoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.dto.ResponseContribuicaoServicoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.mapper.ContribuicaoServicoMapper;
import collectiva.org.collecta.domain.eventoCampanha.EventoCampanha;
import collectiva.org.collecta.domain.eventoCampanha.service.EventoCampanhaService;
import collectiva.org.collecta.enums.StatusContribuicao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContribuicaoServicoServiceFacade {
    private final ContribuicaoServicoService contribuicaoServicoService;
    private final DoadorService doadorService;
    private final EventoCampanhaService eventoCampanhaService;

    public List<ResponseContribuicaoServicoDTO> buscarTodasContribuicoesServicos() {
        return contribuicaoServicoService.buscarTodasContribuicoesServicos().stream().map
                (ContribuicaoServicoMapper::paraDTO).toList();
    }

    public ResponseContribuicaoServicoDTO buscarContribuicaoServicoPorId(UUID id) {
        return ContribuicaoServicoMapper.paraDTO(contribuicaoServicoService.buscarContribuicaoServicoPorId(id));
    }

    public AssociationContribuicaoServicoDTO salvarContribuicaoServico(UUID idDoador, UUID idEvento, CreateContribuicaoServicoDTO contribuicaoServicoDTO) {
        Doador doador = doadorService.buscarDoadorPorId(idDoador);
        EventoCampanha eventoCampanha = eventoCampanhaService.buscarEventoCampanhaPorId(idEvento);
        ContribuicaoServico contribuicaoServico = ContribuicaoServicoMapper.paraEntidade(contribuicaoServicoDTO);
        return ContribuicaoServicoMapper.paraAssociacaoDTO(contribuicaoServicoService.salvarContribuicaoServico(contribuicaoServico, doador, eventoCampanha));
    }

    public AssociationContribuicaoServicoDTO atualizarStatusContribuicao(UUID idContribuicaoServico, StatusContribuicao statusContribuicao) {
        return ContribuicaoServicoMapper.paraAssociacaoDTO(contribuicaoServicoService.atualizarStatusContribuicao(idContribuicaoServico, statusContribuicao));
    }
}
