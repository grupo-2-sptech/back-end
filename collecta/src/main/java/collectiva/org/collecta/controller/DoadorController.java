package collectiva.org.collecta.controller;

import collectiva.org.collecta.authentication.dto.DoadorLoginDTO;
import collectiva.org.collecta.authentication.dto.DoadorTokenDTO;
import collectiva.org.collecta.dto.DoadorDTO;
import collectiva.org.collecta.service.DoadorService;
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
    public ResponseEntity<List<DoadorDTO>> buscarDoadores() {
        List<DoadorDTO> lista = doadorService.buscarTodosDoadores();
        return ResponseEntity.status(lista.isEmpty() ? 204 : 200).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoadorDTO> buscarDoadorPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(doadorService.buscarDoadorPorId(id));
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<DoadorTokenDTO> criarDoador(@RequestBody @Valid DoadorDTO doadorDTO) {
        return ResponseEntity.status(201).body(doadorService.salvarDoador(doadorDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoadorDTO> atualizarDoador(@PathVariable UUID id, @RequestBody @Valid DoadorDTO doadorDTO) {
        return ResponseEntity.ok(doadorService.atualizarDoador(id, doadorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDoador(@PathVariable UUID id) {
        doadorService.deletarDoador(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<DoadorTokenDTO> login(@RequestBody DoadorLoginDTO usuarioLoginDto) {
        DoadorTokenDTO doadorTokenDTO = doadorService.autenticar(usuarioLoginDto);
        return ResponseEntity.ok(doadorTokenDTO);
    }
}
