package collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.service;


import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.conta.doador.service.DoadorService;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.ContribuicaoRecurso;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.dto.AssociationContribuicaoRecursoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.dto.CreateContribuicaoRecursoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.dto.ResponseContribuicaoRecursoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.mapper.ContribuicaoRecursoMapper;
import collectiva.org.collecta.domain.recurso.Recurso;
import collectiva.org.collecta.domain.recurso.service.RecursoService;
import collectiva.org.collecta.enums.StatusContribuicao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContribuicaoRecursoServiceFacade {
    private final ContribuicaoRecursoService contribuicaoRecursoService;
    private final DoadorService doadorService;
    private final RecursoService recursoService;

    public List<ResponseContribuicaoRecursoDTO> buscarTodasContribuicoesRecursos() {
        return contribuicaoRecursoService.buscarTodasContribuicoesRecursos().stream().map
                (ContribuicaoRecursoMapper::paraDTO).toList();
    }

    public ResponseContribuicaoRecursoDTO buscarContribuicaoRecursoPorId(UUID id) {
        return ContribuicaoRecursoMapper.paraDTO(contribuicaoRecursoService.buscarContribuicaoRecursoPorId(id));
    }

    public AssociationContribuicaoRecursoDTO criarContribuicaoRecurso(UUID idDoador, UUID idRecurso, CreateContribuicaoRecursoDTO contribuicaoRecursoDTO) {
        Doador doador = doadorService.buscarDoadorPorId(idDoador);
        Recurso recurso = recursoService.buscarRecursoPorId(idRecurso);
        ContribuicaoRecurso contribuicaoRecurso = ContribuicaoRecursoMapper.paraEntidade(contribuicaoRecursoDTO);
        recursoService.somarContribuicao(recurso, contribuicaoRecurso.getQuantidade());
        return ContribuicaoRecursoMapper.paraAssociacaoDTO(contribuicaoRecursoService.criarContribuicaoRecurso(contribuicaoRecurso, doador, recurso));
    }

    public AssociationContribuicaoRecursoDTO atualizarStatusContribuicao(UUID idContribuicaoRecurso, StatusContribuicao statusContribuicao) {
        return ContribuicaoRecursoMapper.paraAssociacaoDTO(contribuicaoRecursoService.atualizarStatusContribuicao(idContribuicaoRecurso, statusContribuicao));
    }
}
