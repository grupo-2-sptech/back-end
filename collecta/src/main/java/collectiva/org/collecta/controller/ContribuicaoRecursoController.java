package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.CampanhaDTO;
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
        List<ContribuicaoRecursoDTO> lista = contribuicaoRecursoService.buscarTodasContribuicoesRecursos();
        return ResponseEntity.status(lista.isEmpty()? 204 : 200).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContribuicaoRecursoDTO> buscarContribuicaoRecursoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(contribuicaoRecursoService.buscarContribuicaoRecursoPorId(id));
    }

    @PostMapping
    public ResponseEntity<ContribuicaoRecursoDTO> criarContribuicaoRecurso(@RequestBody @Valid ContribuicaoRecursoDTO contribuicaoRecurso) {
        return ResponseEntity.status(201).body(contribuicaoRecursoService.salvarContribuicaoRecurso(contribuicaoRecurso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContribuicaoRecursoDTO> atualizarContribuicaoRecurso(@PathVariable UUID id, @Valid @RequestBody ContribuicaoRecursoDTO contribuicaoRecursoDTO) {
        return ResponseEntity.ok(contribuicaoRecursoService.atualizarContribuicaoRecurso(id, contribuicaoRecursoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContribuicaoRecurso(@PathVariable UUID id) {
        contribuicaoRecursoService.deletarContribuicaoRecurso(id);
        return ResponseEntity.noContent().build();
    }
}
