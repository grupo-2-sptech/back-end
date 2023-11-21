package collectiva.org.collecta.domain.conta.usuario.controller;

import collectiva.org.collecta.authentication.dto.ContaLoginDTO;
import collectiva.org.collecta.authentication.dto.ContaTokenDTO;
import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.conta.doador.dto.CreateDoadorDTO;
import collectiva.org.collecta.domain.conta.doador.dto.ResponseDoadorDTO;
import collectiva.org.collecta.domain.conta.doador.mapper.DoadorMapper;
import collectiva.org.collecta.domain.conta.doador.service.DoadorService;
import collectiva.org.collecta.domain.conta.organizacao.Organizacao;
import collectiva.org.collecta.domain.conta.organizacao.dto.CreateOrganizacaoDTO;
import collectiva.org.collecta.domain.conta.organizacao.dto.ResponseOrganizacaoDTO;
import collectiva.org.collecta.domain.conta.organizacao.mapper.OrganizacaoMapper;
import collectiva.org.collecta.domain.conta.organizacao.service.OrganizacaoService;
import collectiva.org.collecta.domain.conta.usuario.service.ContaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    private final DoadorService doadorService;
    private final OrganizacaoService organizacaoService;

    @PostMapping()
    public ResponseEntity<ContaTokenDTO> login(@RequestBody @Valid ContaLoginDTO usuarioLoginDto) {
        ContaTokenDTO doadorTokenDTO = contaService.autenticar(usuarioLoginDto);
        return ResponseEntity.ok(doadorTokenDTO);
    }

    @PostMapping("/cadastro/doador")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ResponseDoadorDTO> criarDoador(@RequestBody @Valid CreateDoadorDTO doadorDTO) {
        Doador doador = doadorService.salvarDoador(DoadorMapper.paraEntidade(doadorDTO));
        return ResponseEntity.status(201).body(DoadorMapper.paraDTO(doador));
    }

    @PostMapping("/cadastro/organizacao")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ResponseOrganizacaoDTO> criarOrganizacao(@RequestBody @Valid CreateOrganizacaoDTO organizacaoDTO) {
        Organizacao organizacao = organizacaoService.salvarOrganizacao(OrganizacaoMapper.paraEntidade(organizacaoDTO));
        return ResponseEntity.status(201).body(OrganizacaoMapper.paraDTO(organizacao));
    }
}
