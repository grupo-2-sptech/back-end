package collectiva.org.collecta.domain.financeiroCampanha.service;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.campanha.service.CampanhaService;
import collectiva.org.collecta.domain.financeiroCampanha.FinanceiroCampanha;
import collectiva.org.collecta.domain.financeiroCampanha.dto.AssociationFinanceiroCampanhaDTO;
import collectiva.org.collecta.domain.financeiroCampanha.dto.CreateFinanceiroCampanhaDTO;
import collectiva.org.collecta.domain.financeiroCampanha.dto.ResponseFinanceiroCampanhaDTO;
import collectiva.org.collecta.domain.financeiroCampanha.dto.UpdateFinanceiroCampanhaDTO;
import collectiva.org.collecta.domain.financeiroCampanha.mapper.FinanceiroCampanhaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FinanceiroCampanhaServiceFacade {
    private final FinanceiroCampanhaService financeiroCampanhaService;
    private final CampanhaService campanhaService;

    public List<ResponseFinanceiroCampanhaDTO> buscarTodosFinanceiroCampanhas() {
        return financeiroCampanhaService.buscarTodosFinanceirosCampanha().stream().map
                (FinanceiroCampanhaMapper::paraDTO).toList();
    }

    public ResponseFinanceiroCampanhaDTO buscarFinanceiroCampanhaPorId(UUID id) {
        return FinanceiroCampanhaMapper.paraDTO(financeiroCampanhaService.buscarFinanceiroCampanhaPorId(id));
    }


    public AssociationFinanceiroCampanhaDTO criarFinanceiroCampanha(UUID idCampanha, CreateFinanceiroCampanhaDTO financeiroCampanhaDTO) {
        Campanha campanha = campanhaService.buscarCampanhaPorId(idCampanha);
        FinanceiroCampanha financeiroCampanha = FinanceiroCampanhaMapper.paraEntidade(financeiroCampanhaDTO);
        return FinanceiroCampanhaMapper.paraAssociacaoDTO(financeiroCampanhaService.criarFinanceiroCampanha(financeiroCampanha, campanha));
    }

    public AssociationFinanceiroCampanhaDTO atualizarFinanceiroCampanha(UUID idFinanceiroCampanha, UpdateFinanceiroCampanhaDTO financeiroCampanhaDTO) {
        FinanceiroCampanha financeiroCampanha = FinanceiroCampanhaMapper.paraEntidadeUpdate(financeiroCampanhaDTO);
        return FinanceiroCampanhaMapper.paraAssociacaoDTO(financeiroCampanhaService.atualizarFinanceiroCampanha(idFinanceiroCampanha, financeiroCampanha));
    }

    public void deletarFinanceiroCampanha(UUID id) {
        financeiroCampanhaService.deletarFinanceiroCampanha(id);
    }

}
