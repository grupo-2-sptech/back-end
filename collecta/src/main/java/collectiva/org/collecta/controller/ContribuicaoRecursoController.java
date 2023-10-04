package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.ContribuicaoRecursoDTO;
import collectiva.org.collecta.service.ContribuicaoRecursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contribuicoes/recursos")
public class ContribuicaoRecursoController {

    @Autowired
    private ContribuicaoRecursoService contribuicaoRecursoService;

    @GetMapping
    public ResponseEntity<List<ContribuicaoRecursoDTO>> buscarContribuicoesRecursos() {
        return contribuicaoRecursoService.buscarTodasContribuicoesRecursos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ContribuicaoRecursoDTO> buscarContribuicaoRecursoPorId(@PathVariable UUID id) {
        return contribuicaoRecursoService.buscarContribuicaoRecursoPorId(id);
    }

    @PostMapping
    public ResponseEntity<ContribuicaoRecursoDTO> criarContribuicaoRecurso(@RequestBody @Valid ContribuicaoRecursoDTO contribuicaoRecurso) {
        return contribuicaoRecursoService.salvarContribuicaoRecurso(contribuicaoRecurso);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ContribuicaoRecursoDTO> atualizarContribuicaoRecurso(@PathVariable UUID id, @Valid @RequestBody ContribuicaoRecursoDTO contribuicaoRecursoDTO) {
        return contribuicaoRecursoService.atualizarContribuicaoRecurso(id, contribuicaoRecursoDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContribuicaoRecurso(@PathVariable UUID id){
        return  contribuicaoRecursoService.deletarContribuicaoRecurso(id);
    }
}
