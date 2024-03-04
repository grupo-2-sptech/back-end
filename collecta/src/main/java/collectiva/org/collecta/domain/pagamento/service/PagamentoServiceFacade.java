package collectiva.org.collecta.domain.pagamento.service;

import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.ContribuicaoMonetaria;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.service.ContribuicaoMonetariaService;
import collectiva.org.collecta.domain.pagamento.Pagamento;
import collectiva.org.collecta.domain.pagamento.dto.AssociationPagamentoDTO;
import collectiva.org.collecta.domain.pagamento.dto.CreatePagamentoDTO;
import collectiva.org.collecta.domain.pagamento.dto.ResponsePagamentoDTO;
import collectiva.org.collecta.domain.pagamento.mapper.PagamentoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagamentoServiceFacade {
    private final PagamentoService pagamentoService;
    private final ContribuicaoMonetariaService contribuicaoMonetariaService;

    public List<ResponsePagamentoDTO> buscarTodosPagamentos() {
        return pagamentoService.buscarTodosPagamentos().stream().map
                (PagamentoMapper::paraDTO).toList();
    }

    public ResponsePagamentoDTO buscarPagamentoPorId(UUID id) {
        return PagamentoMapper.paraDTO(pagamentoService.buscarPagamentoPorId(id));
    }

    public AssociationPagamentoDTO criarPagamento(UUID idEventoCampanha, CreatePagamentoDTO pagamentoDTO) {
        Pagamento pagamento = PagamentoMapper.paraEntidade(pagamentoDTO);
        ContribuicaoMonetaria contribuicaoMonetaria = contribuicaoMonetariaService.buscarContribuicaoMonetariaPorId(idEventoCampanha);
        return PagamentoMapper.paraAssociacaoDTO(pagamentoService.criarPagamento(pagamento, contribuicaoMonetaria));
    }

    public void deletarPagamento(UUID id) {
        pagamentoService.deletarPagamento(id);
    }

}
