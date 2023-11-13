package collectiva.org.collecta.domain.conta.Doador.controller;

import collectiva.org.collecta.authentication.dto.DoadorLoginDTO;
import collectiva.org.collecta.authentication.dto.DoadorTokenDTO;
import collectiva.org.collecta.domain.conta.Doador.Doador;
import collectiva.org.collecta.domain.conta.Doador.dto.DoadorDTO;
import collectiva.org.collecta.domain.conta.Doador.mapper.DoadorMapper;
import collectiva.org.collecta.domain.conta.Doador.service.DoadorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doadores")
@RequiredArgsConstructor
public class DoadorController {
    private final DoadorService doadorService;

    @GetMapping
    public ResponseEntity<List<DoadorDTO>> buscarDoadores() {
        List<DoadorDTO> listaDTO = doadorService.buscarTodosDoadores().stream()
                .map(DoadorMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoadorDTO> buscarDoadorPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(DoadorMapper.paraDTO(doadorService.buscarDoadorPorId(id)));
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<DoadorDTO> criarDoador(@RequestBody @Valid DoadorDTO doadorDTO) {
        Doador doador = doadorService.salvarDoador(DoadorMapper.paraEntidade(doadorDTO));
        return ResponseEntity.status(201).body(DoadorMapper.paraDTO(doador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoadorDTO> atualizarDoador(@PathVariable UUID id, @RequestBody @Valid DoadorDTO doadorDTO) {
        Doador doador = doadorService.atualizarDoador(id, DoadorMapper.paraEntidade(doadorDTO));
        return ResponseEntity.ok(DoadorMapper.paraDTO(doador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDoador(@PathVariable UUID id) {
        doadorService.deletarDoador(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<DoadorTokenDTO> login(@RequestBody @Valid DoadorLoginDTO usuarioLoginDto) {
        DoadorTokenDTO doadorTokenDTO = doadorService.autenticar(usuarioLoginDto);
        return ResponseEntity.ok(doadorTokenDTO);
    }
}
