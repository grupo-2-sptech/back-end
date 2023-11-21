package collectiva.org.collecta.domain.plano.controller;

import collectiva.org.collecta.domain.plano.Plano;
import collectiva.org.collecta.domain.plano.dto.CreatePlanoDTO;
import collectiva.org.collecta.domain.plano.dto.ResponsePlanoDTO;
import collectiva.org.collecta.domain.plano.mapper.PlanoMapper;
import collectiva.org.collecta.domain.plano.service.PlanoService;
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
    private final PlanoService planoService;

    @GetMapping
    public ResponseEntity<List<ResponsePlanoDTO>> buscarPlanos() {
        List<ResponsePlanoDTO> listaDTO = planoService.buscarTodosPlanos().stream()
                .map(PlanoMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePlanoDTO> buscarPlanoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(PlanoMapper.paraDTO(planoService.buscarPlanoPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ResponsePlanoDTO> criarPlano(@RequestBody @Valid CreatePlanoDTO planoDTO) {
        Plano plano = planoService.salvarPlano(PlanoMapper.paraEntidade(planoDTO));
        return ResponseEntity.status(201).body(PlanoMapper.paraDTO(plano));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsePlanoDTO> atualizarPlano(@PathVariable UUID id, @RequestBody @Valid CreatePlanoDTO planoDTO) {
        Plano plano = planoService.atualizarPlano(id, PlanoMapper.paraEntidade(planoDTO));
        return ResponseEntity.ok(PlanoMapper.paraDTO(plano));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPlano(@PathVariable UUID id) {
        planoService.deletarPlano(id);
        return ResponseEntity.noContent().build();
    }
}
