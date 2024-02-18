package collectiva.org.collecta.domain.acaoCampanha.service;

import collectiva.org.collecta.domain.acaoCampanha.AcaoCampanha;
import collectiva.org.collecta.domain.acaoCampanha.dto.AssociationAcaoCampanhaDTO;
import collectiva.org.collecta.domain.acaoCampanha.dto.CreateAcaoCampanhaDTO;
import collectiva.org.collecta.domain.acaoCampanha.dto.ResponseAcaoCampanhaDTO;
import collectiva.org.collecta.domain.acaoCampanha.dto.UpdateAcaoCampanhaDTO;
import collectiva.org.collecta.domain.acaoCampanha.mapper.AcaoCampanhaMapper;
import collectiva.org.collecta.domain.acaoCampanha.repository.AcaoCampanhaRepository;
import collectiva.org.collecta.domain.relatorio.service.RelatorioService;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AcaoCampanhaService {
    private final AcaoCampanhaRepository acoesRepository;
    private final RelatorioService relatorioService;

    public AcaoCampanha buscarExisteAcao(UUID id) {
        return acoesRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("AcaoCampanha"));
    }

    public AssociationAcaoCampanhaDTO criarAcaoCampanha(CreateAcaoCampanhaDTO acao, UUID idRelatorio) {
        AcaoCampanha acaoCampanha = AcaoCampanhaMapper.paraEntidade(acao);
        acaoCampanha.setRelatorio(relatorioService.buscarRelatorioPorId(idRelatorio));
        return AcaoCampanhaMapper.paraAssociacaoDTO(acoesRepository.save(acaoCampanha));
    }

    public List<ResponseAcaoCampanhaDTO> buscarTodosAcoes() {
        return acoesRepository.findAll().stream().map(AcaoCampanhaMapper::paraDTO).toList();
    }

    public ResponseAcaoCampanhaDTO buscarAcaoCampanhaPorId(UUID id) {
        return AcaoCampanhaMapper.paraDTO(buscarExisteAcao(id));
    }

    public AssociationAcaoCampanhaDTO atualizarAcaoCampanha(UUID id, UpdateAcaoCampanhaDTO acao) {
        AcaoCampanha existeAcao = buscarExisteAcao(id);
        AcaoCampanha acaoCampanha = AcaoCampanhaMapper.paraEntidadeUpdate(acao);
        acaoCampanha.setId(id);
        acaoCampanha.setRelatorio(existeAcao.getRelatorio());
        return AcaoCampanhaMapper.paraAssociacaoDTO(acoesRepository.save(acaoCampanha));
    }

    public void deletarAcaoCampanha(UUID id) {
        buscarExisteAcao(id);
        acoesRepository.deleteById(id);
    }

}

