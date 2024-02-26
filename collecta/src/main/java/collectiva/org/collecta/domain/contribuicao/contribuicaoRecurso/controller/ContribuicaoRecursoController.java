package collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.controller;

import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.dto.AssociationContribuicaoRecursoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.dto.CreateContribuicaoRecursoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.dto.ResponseContribuicaoRecursoDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.service.ContribuicaoRecursoServiceFacade;
import collectiva.org.collecta.enums.StatusContribuicao;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contribuicoes/recursos")
@RequiredArgsConstructor
public class ContribuicaoRecursoController {
    private final ContribuicaoRecursoServiceFacade contribuicaoRecursoServiceF;

    @GetMapping
    public ResponseEntity<List<ResponseContribuicaoRecursoDTO>> buscarContribuicoesRecursos() {
        List<ResponseContribuicaoRecursoDTO> listaDTO = contribuicaoRecursoServiceF.buscarTodasContribuicoesRecursos();
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseContribuicaoRecursoDTO> buscarContribuicaoRecursoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(contribuicaoRecursoServiceF.buscarContribuicaoRecursoPorId(id));
    }

    @PostMapping("/{idDoador}/{idRecurso}")
    public ResponseEntity<AssociationContribuicaoRecursoDTO> criarContribuicaoRecurso(@PathVariable UUID idDoador, @PathVariable UUID idRecurso, @RequestBody @Valid CreateContribuicaoRecursoDTO contribuicaoRecursoDTO) {
        return ResponseEntity.status(201).body(contribuicaoRecursoServiceF.salvarContribuicaoRecurso(idDoador, idRecurso, contribuicaoRecursoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationContribuicaoRecursoDTO> atualizarStatusCampanha(@PathVariable UUID id, @RequestParam StatusContribuicao statusContribuicao) {
        return ResponseEntity.ok(contribuicaoRecursoServiceF.atualizarStatusContribuicao(id, statusContribuicao));
    }

}
