package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.PlanoDTO;
import collectiva.org.collecta.service.PlanoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/planos")
public class PlanoController {

    @Autowired
    private PlanoService planoService;

    @GetMapping
    public ResponseEntity<List<PlanoDTO>> buscarPlanos() {
        return planoService.buscarTodosPlanos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PlanoDTO> buscarPlanoPorId(@PathVariable UUID id) {
        return planoService.buscarPlanoPorId(id);
    }

    @PostMapping
    public ResponseEntity<PlanoDTO> criarPlano(@RequestBody @Valid PlanoDTO planoDTO) {
        return planoService.salvarPlano(planoDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PlanoDTO> atualizarPlano(@PathVariable UUID id, @RequestBody @Valid PlanoDTO planoDTO) {
        return planoService.atualizarPlano(id, planoDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPlano(@PathVariable UUID id){
        return  planoService.deletarPlano(id);
    }
}
