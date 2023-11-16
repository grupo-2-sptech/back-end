package collectiva.org.collecta.domain.conta.usuario.controller;

import collectiva.org.collecta.authentication.dto.ContaLoginDTO;
import collectiva.org.collecta.authentication.dto.ContaTokenDTO;
import collectiva.org.collecta.domain.conta.usuario.service.ContaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final ContaService contaService;
    @PostMapping()
    public ResponseEntity<ContaTokenDTO> login(@RequestBody @Valid ContaLoginDTO usuarioLoginDto) {
        ContaTokenDTO doadorTokenDTO = contaService.autenticar(usuarioLoginDto);
        return ResponseEntity.ok(doadorTokenDTO);
    }
}
