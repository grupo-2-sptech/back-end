package collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.service;


import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.conta.doador.service.DoadorService;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.ContribuicaoMonetaria;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.dto.AssociationContribuicaoMonetariaDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.dto.CreateContribuicaoMonetariaDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.dto.ResponseContribuicaoMonetariaDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.mapper.ContribuicaoMonetariaMapper;
import collectiva.org.collecta.domain.financeiroCampanha.FinanceiroCampanha;
import collectiva.org.collecta.domain.financeiroCampanha.service.FinanceiroCampanhaService;
import collectiva.org.collecta.enums.StatusContribuicao;
import collectiva.org.collecta.integration.pix.cob.json.PixCreateImmediateCharge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContribuicaoMonetariaServiceFacade {
    private final ContribuicaoMonetariaService contribuicaoMonetariaService;
    private final DoadorService doadorService;
    private final FinanceiroCampanhaService financeiroCampanhaService;

    public List<ResponseContribuicaoMonetariaDTO> buscarTodasContribuicoesMonetarias() {
        return contribuicaoMonetariaService.buscarTodasContribuicoesMonetarias().stream().map
                (ContribuicaoMonetariaMapper::paraDTO).toList();
    }

    public ResponseContribuicaoMonetariaDTO buscarContribuicaoMonetariaPorId(UUID id) {
        return ContribuicaoMonetariaMapper.paraDTO(contribuicaoMonetariaService.buscarContribuicaoMonetariaPorId(id));
    }

    public AssociationContribuicaoMonetariaDTO criarContribuicaoMonetaria(UUID idDoador, UUID idFinanceiro, CreateContribuicaoMonetariaDTO contribuicaoMonetariaDTO) {
        Doador doador = doadorService.buscarDoadorPorId(idDoador);
        List<String> dadosPix = PixCreateImmediateCharge.CobrancaRapida(doador.getNome(), doador.getCpf(), contribuicaoMonetariaDTO.getValor().toString());
        String idCodigoPix = dadosPix.get(0);
        String txid = dadosPix.get(1);
        FinanceiroCampanha financeiroCampanha = financeiroCampanhaService.buscarFinanceiroCampanhaPorId(idFinanceiro);
        ContribuicaoMonetaria contribuicaoMonetaria = ContribuicaoMonetariaMapper.paraEntidade(contribuicaoMonetariaDTO, idCodigoPix, txid);
        return ContribuicaoMonetariaMapper.paraAssociacaoDTO(contribuicaoMonetariaService.criarContribuicaoMonetaria(contribuicaoMonetaria, doador, financeiroCampanha));
    }

    public AssociationContribuicaoMonetariaDTO atualizarStatusContribuicao(UUID idContribuicaoMonetaria, StatusContribuicao statusContribuicao) {
        return ContribuicaoMonetariaMapper.paraAssociacaoDTO(contribuicaoMonetariaService.atualizarStatusContribuicao(idContribuicaoMonetaria, statusContribuicao));
    }
}
