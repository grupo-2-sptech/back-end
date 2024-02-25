package collectiva.org.collecta.domain.conta.doador.controller;

import collectiva.org.collecta.domain.conta.doador.dto.AssociationDoadorDTO;
import collectiva.org.collecta.domain.conta.doador.dto.ResponseDoadorDTO;
import collectiva.org.collecta.domain.conta.doador.dto.UpdateDoadorDTO;
import collectiva.org.collecta.domain.conta.doador.service.DoadorServiceFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/doadores")
@RequiredArgsConstructor
public class DoadorController {
    private final DoadorServiceFacade doadorServiceF;

    @GetMapping
    public ResponseEntity<List<ResponseDoadorDTO>> buscarDoadores() {
        List<ResponseDoadorDTO> listaDTO = doadorServiceF.buscarTodosDoadores();
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDoadorDTO> buscarDoadorPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(doadorServiceF.buscarDoadorPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationDoadorDTO> atualizarDoador(@PathVariable UUID id, @RequestBody @Valid UpdateDoadorDTO doadorDTO) {
        return ResponseEntity.ok(doadorServiceF.atualizarDoador(id, doadorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDoador(@PathVariable UUID id) {
        doadorServiceF.deletarDoador(id);
        return ResponseEntity.noContent().build();
    }
}
