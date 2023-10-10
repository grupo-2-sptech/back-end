package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.FinanceiroCampanha;
import collectiva.org.collecta.dto.FinanceiroCampanhaDTO;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoContemElementosException;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import collectiva.org.collecta.mapper.FinanceiroCampanhaMapper;
import collectiva.org.collecta.repository.FinanceiroCampanhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinanceiroCampanhaService {
    private final FinanceiroCampanhaRepository finaceiroCampanhaRepository;

    public FinanceiroCampanhaDTO salvarFinanceiroCampanha(FinanceiroCampanhaDTO finaceiroCampanhaDTO) {
        FinanceiroCampanha finaceiroCampanha = FinanceiroCampanhaMapper.paraEntidade(finaceiroCampanhaDTO);
        finaceiroCampanhaRepository.save(finaceiroCampanha);
        return FinanceiroCampanhaMapper.paraDTO(finaceiroCampanha);
    }

    public List<FinanceiroCampanhaDTO> buscarTodosFinanceirosCampanha() {
        List<FinanceiroCampanha> financeirosCampanha = finaceiroCampanhaRepository.findAll();
        if (financeirosCampanha.isEmpty()){
            throw new EntidadeNaoContemElementosException("Financeiro");
        }
        return financeirosCampanha.stream().map(FinanceiroCampanhaMapper::paraDTO).collect(Collectors.toList());
    }

    public FinanceiroCampanhaDTO buscarFinanceiroCampanhaPorId(UUID id) {
        return FinanceiroCampanhaMapper.paraDTO(finaceiroCampanhaRepository.findById(id).orElseThrow(
            () -> new EntidadeNaoEncontradaException("Financeiro")));
    }

    public FinanceiroCampanhaDTO atualizarFinanceiroCampanha(UUID id, FinanceiroCampanhaDTO finaceiroCampanhaDTO) {
        buscarFinanceiroCampanhaPorId(id);
        FinanceiroCampanha financeiroNovo = FinanceiroCampanhaMapper.paraEntidade(finaceiroCampanhaDTO);
        financeiroNovo.setId(id);
        finaceiroCampanhaRepository.save(financeiroNovo);
        return FinanceiroCampanhaMapper.paraDTO(financeiroNovo);
    }

    public void deletarFinanceiroCampanha(UUID id) {
        if (!finaceiroCampanhaRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Financeiro");
        }
        finaceiroCampanhaRepository.deleteById(id);
    }
}

