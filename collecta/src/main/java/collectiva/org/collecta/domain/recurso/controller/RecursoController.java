package collectiva.org.collecta.domain.recurso.controller;

import collectiva.org.collecta.domain.recurso.dto.AssociationRecursoDTO;
import collectiva.org.collecta.domain.recurso.dto.CreateRecursoDTO;
import collectiva.org.collecta.domain.recurso.dto.ResponseRecursoDTO;
import collectiva.org.collecta.domain.recurso.dto.UpdateRecursoDTO;
import collectiva.org.collecta.domain.recurso.service.RecursoServiceFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/recursos")
@RequiredArgsConstructor
public class RecursoController {
    private final RecursoServiceFacade recursoServiceF;

    @GetMapping
    public ResponseEntity<List<ResponseRecursoDTO>> buscarRecursos() {
        List<ResponseRecursoDTO> listaDTO = recursoServiceF.buscarTodosRecursos();
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseRecursoDTO> buscarRecursoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(recursoServiceF.buscarRecursoPorId(id));
    }

    @PostMapping("/{idCampanha}")
    public ResponseEntity<AssociationRecursoDTO> criarRecurso(@PathVariable UUID idCampanha, @RequestBody @Valid CreateRecursoDTO recursoDTO) {
        return ResponseEntity.status(201).body(recursoServiceF.criarRecurso(idCampanha, recursoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationRecursoDTO> atualizarRecurso(@PathVariable UUID id, @RequestBody @Valid UpdateRecursoDTO recursoDTO) {
        return ResponseEntity.ok(recursoServiceF.atualizarRecurso(id, recursoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRecurso(@PathVariable UUID id) {
        recursoServiceF.deletarRecurso(id);
        return ResponseEntity.noContent().build();
    }
}
