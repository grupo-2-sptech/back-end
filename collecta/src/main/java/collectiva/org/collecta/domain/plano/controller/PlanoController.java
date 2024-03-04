package collectiva.org.collecta.domain.plano.controller;

import collectiva.org.collecta.domain.plano.dto.AssociationPlanoDTO;
import collectiva.org.collecta.domain.plano.dto.CreatePlanoDTO;
import collectiva.org.collecta.domain.plano.dto.ResponsePlanoDTO;
import collectiva.org.collecta.domain.plano.dto.UpdatePlanoDTO;
import collectiva.org.collecta.domain.plano.service.PlanoServiceFacade;
import collectiva.org.collecta.enums.StatusPlano;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/planos")
@RequiredArgsConstructor
public class PlanoController {
    private final PlanoServiceFacade planoServiceF;

    @GetMapping
    public ResponseEntity<List<ResponsePlanoDTO>> buscarPlanos() {
        List<ResponsePlanoDTO> listaDTO = planoServiceF.buscarTodosPlanos();
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePlanoDTO> buscarPlanoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(planoServiceF.buscarPlanoPorId(id));
    }

    @PostMapping("/{idDoador}")
    public ResponseEntity<AssociationPlanoDTO> criarPlano(@PathVariable UUID idDoador, @RequestBody @Valid CreatePlanoDTO planoDTO) {
        return ResponseEntity.status(201).body(planoServiceF.criarPlano(idDoador, planoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationPlanoDTO> atualizarPlano(@PathVariable UUID id, @RequestBody @Valid UpdatePlanoDTO planoDTO) {
        return ResponseEntity.ok(planoServiceF.atualizarPlano(id, planoDTO));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<AssociationPlanoDTO> atualizarStatusPlano(@PathVariable UUID id, @RequestParam StatusPlano statusPlano) {
        return ResponseEntity.ok(planoServiceF.atualizarStatusPlano(id, statusPlano));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPlano(@PathVariable UUID id) {
        planoServiceF.deletarPlano(id);
        return ResponseEntity.noContent().build();
    }
}
