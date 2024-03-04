package collectiva.org.collecta.domain.plano.service;

import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.conta.doador.service.DoadorService;
import collectiva.org.collecta.domain.plano.Plano;
import collectiva.org.collecta.domain.plano.dto.AssociationPlanoDTO;
import collectiva.org.collecta.domain.plano.dto.CreatePlanoDTO;
import collectiva.org.collecta.domain.plano.dto.ResponsePlanoDTO;
import collectiva.org.collecta.domain.plano.dto.UpdatePlanoDTO;
import collectiva.org.collecta.domain.plano.mapper.PlanoMapper;
import collectiva.org.collecta.enums.StatusPlano;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlanoServiceFacade {
    private final PlanoService planoService;
    private final DoadorService doadorService;

    public List<ResponsePlanoDTO> buscarTodosPlanos() {
        return planoService.buscarTodosPlanos().stream().map
                (PlanoMapper::paraDTO).toList();
    }

    public ResponsePlanoDTO buscarPlanoPorId(UUID id) {
        return PlanoMapper.paraDTO(planoService.buscarPlanoPorId(id));
    }

    public AssociationPlanoDTO criarPlano(UUID idEventoCampanha, CreatePlanoDTO planoDTO) {
        Doador doador = doadorService.buscarDoadorPorId(idEventoCampanha);
        Plano plano = PlanoMapper.paraEntidade(planoDTO);
        return PlanoMapper.paraAssociacaoDTO(planoService.criarPlano(plano, doador));
    }

    public AssociationPlanoDTO atualizarPlano(UUID idPlano, UpdatePlanoDTO planoDTO) {
        Plano plano = PlanoMapper.paraEntidadeUpdate(planoDTO);
        return PlanoMapper.paraAssociacaoDTO(planoService.atualizarPlano(idPlano, plano));
    }
    public AssociationPlanoDTO atualizarStatusPlano(UUID idPlano, StatusPlano statusPlano) {
        return PlanoMapper.paraAssociacaoDTO(planoService.atualizarStatusPlano(idPlano, statusPlano));
    }


    public void deletarPlano(UUID id) {
        planoService.deletarPlano(id);
    }

}
