package collectiva.org.collecta.domain.conta.organizacao.controller;

import collectiva.org.collecta.domain.conta.organizacao.Organizacao;
import collectiva.org.collecta.domain.conta.organizacao.dto.OrganizacaoDTO;
import collectiva.org.collecta.domain.conta.organizacao.mapper.OrganizacaoMapper;
import collectiva.org.collecta.domain.conta.organizacao.service.OrganizacaoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/organizacoes")
@RequiredArgsConstructor
public class OrganizacaoController {
    private final OrganizacaoService organizacaoService;

    @GetMapping
    public ResponseEntity<List<OrganizacaoDTO>> buscarOrganizacoes() {
        List<OrganizacaoDTO> listaDTO = organizacaoService.buscarTodasOrganizacoes().stream()
                .map(OrganizacaoMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizacaoDTO> buscarOrganizacaoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(OrganizacaoMapper.paraDTO(organizacaoService.buscarOrganizacaoPorId(id)));
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<OrganizacaoDTO> criarOrganizacao(@RequestBody @Valid OrganizacaoDTO organizacaoDTO) {
        Organizacao organizacao = organizacaoService.salvarOrganizacao(OrganizacaoMapper.paraEntidade(organizacaoDTO));
        return ResponseEntity.status(201).body(OrganizacaoMapper.paraDTO(organizacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizacaoDTO> atualizarOrganizacao(@PathVariable UUID id, @RequestBody @Valid OrganizacaoDTO organizacaoDTO) {
        Organizacao organizacao = organizacaoService.atualizarOrganizacao(id, OrganizacaoMapper.paraEntidade(organizacaoDTO));
        return ResponseEntity.ok(OrganizacaoMapper.paraDTO(organizacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOrganizacao(@PathVariable UUID id) {
        organizacaoService.deletarOrganizacao(id);
        return ResponseEntity.noContent().build();
    }
}
