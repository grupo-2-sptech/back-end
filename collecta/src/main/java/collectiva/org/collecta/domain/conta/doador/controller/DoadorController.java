package collectiva.org.collecta.domain.conta.doador.controller;

import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.conta.doador.dto.CreateDoadorDTO;
import collectiva.org.collecta.domain.conta.doador.dto.ResponseDoadorDTO;
import collectiva.org.collecta.domain.conta.doador.dto.UpdateDoadorDTO;
import collectiva.org.collecta.domain.conta.doador.mapper.DoadorMapper;
import collectiva.org.collecta.domain.conta.doador.service.DoadorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    private final DoadorService doadorService;

    @GetMapping
    public ResponseEntity<List<ResponseDoadorDTO>> buscarDoadores() {
        List<ResponseDoadorDTO> listaDTO = doadorService.buscarTodosDoadores().stream()
                .map(DoadorMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDoadorDTO> buscarDoadorPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(DoadorMapper.paraDTO(doadorService.buscarDoadorPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDoadorDTO> atualizarDoador(@PathVariable UUID id, @RequestBody @Valid UpdateDoadorDTO doadorDTO) {
        Doador doador = doadorService.atualizarDoador(id, DoadorMapper.paraEntidadeUpdate(doadorDTO));
        return ResponseEntity.ok(DoadorMapper.paraDTO(doador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDoador(@PathVariable UUID id) {
        doadorService.deletarDoador(id);
        return ResponseEntity.noContent().build();
    }
}
