package collectiva.org.collecta.domain.acaoCampanha.controller;

import collectiva.org.collecta.domain.acaoCampanha.dto.AssociationAcaoCampanhaDTO;
import collectiva.org.collecta.domain.acaoCampanha.dto.CreateAcaoCampanhaDTO;
import collectiva.org.collecta.domain.acaoCampanha.dto.ResponseAcaoCampanhaDTO;
import collectiva.org.collecta.domain.acaoCampanha.dto.UpdateAcaoCampanhaDTO;
import collectiva.org.collecta.domain.acaoCampanha.service.AcaoCampanhaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/acoes")
@RequiredArgsConstructor
public class AcaoCampanhaController {
    private final AcaoCampanhaService acaoCampanhaService;

    @GetMapping
    public ResponseEntity<List<ResponseAcaoCampanhaDTO>> buscarAcoes() {
        List<ResponseAcaoCampanhaDTO> listaDTO = acaoCampanhaService.buscarTodosAcoes();
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAcaoCampanhaDTO> buscarAcaoCampanhaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(acaoCampanhaService.buscarAcaoCampanhaPorId(id));
    }

    @PostMapping("/{idRelatorio}")
    public ResponseEntity<AssociationAcaoCampanhaDTO> criarAcaoCampanha(@PathVariable UUID idRelatorio, @RequestBody @Valid CreateAcaoCampanhaDTO acoesDTO) {
        return ResponseEntity.status(201).body(acaoCampanhaService.criarAcaoCampanha(acoesDTO, idRelatorio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationAcaoCampanhaDTO> atualizarAcaoCampanha(@PathVariable UUID id, @RequestBody @Valid UpdateAcaoCampanhaDTO acoesDTO) {
        return ResponseEntity.ok(acaoCampanhaService.atualizarAcaoCampanha(id, acoesDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAcaoCampanha(@PathVariable UUID id) {
        acaoCampanhaService.deletarAcaoCampanha(id);
        return ResponseEntity.noContent().build();
    }
}
