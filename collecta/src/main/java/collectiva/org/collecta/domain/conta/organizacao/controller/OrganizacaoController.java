package collectiva.org.collecta.domain.conta.organizacao.controller;

import collectiva.org.collecta.domain.conta.organizacao.Organizacao;
import collectiva.org.collecta.domain.conta.organizacao.dto.AssociationOrganizacaoDTO;
import collectiva.org.collecta.domain.conta.organizacao.dto.CreateOrganizacaoDTO;
import collectiva.org.collecta.domain.conta.organizacao.dto.ResponseOrganizacaoDTO;
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
    public ResponseEntity<List<ResponseOrganizacaoDTO>> buscarOrganizacoes() {
        List<ResponseOrganizacaoDTO> listaDTO = organizacaoService.buscarTodasOrganizacoes().stream()
                .map(OrganizacaoMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseOrganizacaoDTO> buscarOrganizacaoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(OrganizacaoMapper.paraDTO(organizacaoService.buscarOrganizacaoPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationOrganizacaoDTO> atualizarOrganizacao(@PathVariable UUID id, @RequestBody @Valid CreateOrganizacaoDTO organizacaoDTO) {
        Organizacao organizacao = organizacaoService.atualizarOrganizacao(id, OrganizacaoMapper.paraEntidade(organizacaoDTO));
        return ResponseEntity.ok(OrganizacaoMapper.paraAssociacaoDTO(organizacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOrganizacao(@PathVariable UUID id) {
        organizacaoService.deletarOrganizacao(id);
        return ResponseEntity.noContent().build();
    }
}
