package collectiva.org.collecta.domain.acaoCampanha.service;

import collectiva.org.collecta.domain.acaoCampanha.AcaoCampanha;
import collectiva.org.collecta.domain.acaoCampanha.dto.AssociationAcaoCampanhaDTO;
import collectiva.org.collecta.domain.acaoCampanha.dto.CreateAcaoCampanhaDTO;
import collectiva.org.collecta.domain.acaoCampanha.dto.ResponseAcaoCampanhaDTO;
import collectiva.org.collecta.domain.acaoCampanha.dto.UpdateAcaoCampanhaDTO;
import collectiva.org.collecta.domain.acaoCampanha.mapper.AcaoCampanhaMapper;
import collectiva.org.collecta.domain.relatorio.Relatorio;
import collectiva.org.collecta.domain.relatorio.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AcaoCampanhaServiceFacade {
    private final AcaoCampanhaService acaoCampanhaService;
    private final RelatorioService relatorioService;

    public List<ResponseAcaoCampanhaDTO> buscarTodosAcoes() {
        return acaoCampanhaService.buscarTodosAcoes().stream().map
                (AcaoCampanhaMapper::paraDTO).toList();
    }

    public ResponseAcaoCampanhaDTO buscarAcaoCampanhaPorId(UUID id) {
        return AcaoCampanhaMapper.paraDTO(acaoCampanhaService.buscarAcaoCampanhaPorId(id));
    }

    public AssociationAcaoCampanhaDTO criarAcaoCampanha(UUID idRelatorio, CreateAcaoCampanhaDTO acaoCampanhaDTO) {
        Relatorio relatorio = relatorioService.buscarRelatorioPorId(idRelatorio);
        AcaoCampanha acaoCampanha = AcaoCampanhaMapper.paraEntidade(acaoCampanhaDTO);
        return AcaoCampanhaMapper.paraAssociacaoDTO(acaoCampanhaService.criarAcaoCampanha(acaoCampanha, relatorio));
    }

    public AssociationAcaoCampanhaDTO atualizarAcaoCampanha(UUID idAcaoCampanha, UpdateAcaoCampanhaDTO acaoCampanhaDTO) {
        AcaoCampanha acaoCampanha = AcaoCampanhaMapper.paraEntidadeUpdate(acaoCampanhaDTO);
        return AcaoCampanhaMapper.paraAssociacaoDTO(acaoCampanhaService.atualizarAcaoCampanha(idAcaoCampanha, acaoCampanha));
    }

    public void deletarAcaoCampanha(UUID id) {
        acaoCampanhaService.deletarAcaoCampanha(id);
    }

}
