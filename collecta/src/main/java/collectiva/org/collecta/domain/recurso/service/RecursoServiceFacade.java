package collectiva.org.collecta.domain.recurso.service;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.campanha.service.CampanhaService;
import collectiva.org.collecta.domain.recurso.Recurso;
import collectiva.org.collecta.domain.recurso.dto.AssociationRecursoDTO;
import collectiva.org.collecta.domain.recurso.dto.CreateRecursoDTO;
import collectiva.org.collecta.domain.recurso.dto.ResponseRecursoDTO;
import collectiva.org.collecta.domain.recurso.dto.UpdateRecursoDTO;
import collectiva.org.collecta.domain.recurso.mapper.RecursoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecursoServiceFacade {
    private final RecursoService recursoService;
    private final CampanhaService campanhaService;

    public List<ResponseRecursoDTO> buscarTodosRecursos() {
        return recursoService.buscarTodosRecursos().stream().map
                (RecursoMapper::paraDTO).toList();
    }

    public ResponseRecursoDTO buscarRecursoPorId(UUID id) {
        return RecursoMapper.paraDTO(recursoService.buscarRecursoPorId(id));
    }

    public AssociationRecursoDTO criarRecurso(UUID idEventoCampanha, CreateRecursoDTO recursoDTO) {
        Campanha campanha = campanhaService.buscarCampanhaPorId(idEventoCampanha);
        Recurso recurso = RecursoMapper.paraEntidade(recursoDTO);
        return RecursoMapper.paraAssociacaoDTO(recursoService.criarRecurso(recurso, campanha));
    }

    public AssociationRecursoDTO atualizarRecurso(UUID idRecurso, UpdateRecursoDTO recursoDTO) {
        Recurso recurso = RecursoMapper.paraEntidadeUpdate(recursoDTO);
        return RecursoMapper.paraAssociacaoDTO(recursoService.atualizarRecurso(idRecurso, recurso));
    }

    public void deletarRecurso(UUID id) {
        recursoService.deletarRecurso(id);
    }

}
