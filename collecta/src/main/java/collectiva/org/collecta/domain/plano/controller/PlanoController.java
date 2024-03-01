package collectiva.org.collecta.domain.plano.controller;

import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.conta.doador.service.DoadorService;
import collectiva.org.collecta.domain.plano.Plano;
import collectiva.org.collecta.domain.plano.dto.AssociationPlanoDTO;
import collectiva.org.collecta.domain.plano.dto.CreatePlanoDTO;
import collectiva.org.collecta.domain.plano.dto.ResponsePlanoDTO;
import collectiva.org.collecta.domain.plano.dto.UpdatePlanoDTO;
import collectiva.org.collecta.domain.plano.mapper.PlanoMapper;
import collectiva.org.collecta.domain.plano.service.PlanoService;
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
    private final PlanoService planoService;
    private final DoadorService doadorService;

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
    public ResponseEntity<AssociationPlanoDTO> criarPlano(@RequestBody @Valid CreatePlanoDTO planoDTO) {
        Doador doador = doadorService.buscarDoadorPorId(planoDTO.getIdDoador());
        Plano plano = planoService.criarPlano(PlanoMapper.paraEntidade(planoDTO), doador);
        return ResponseEntity.status(201).body(PlanoMapper.paraAssociacaoDTO(plano));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationPlanoDTO> atualizarPlano(@PathVariable UUID id, @RequestBody @Valid UpdatePlanoDTO planoDTO) {
        Plano plano = planoService.atualizarPlano(id, PlanoMapper.paraEntidadeUpdate(planoDTO));
        return ResponseEntity.ok(PlanoMapper.paraAssociacaoDTO(plano));
    }
    @PutMapping("/status/{id}")
    public ResponseEntity<AssociationPlanoDTO> atualizarStatusPlano(@PathVariable UUID id, @RequestParam StatusPlano statusPlano) {
        Plano plano = planoService.atualizarStatusPlano(id, statusPlano);
        return ResponseEntity.ok(PlanoMapper.paraAssociacaoDTO(plano));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPlano(@PathVariable UUID id) {
        planoService.deletarPlano(id);
        return ResponseEntity.noContent().build();
    }
}
