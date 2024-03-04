package collectiva.org.collecta.domain.conta.organizacao.controller;

import collectiva.org.collecta.domain.conta.organizacao.dto.AssociationOrganizacaoDTO;
import collectiva.org.collecta.domain.conta.organizacao.dto.ResponseOrganizacaoDTO;
import collectiva.org.collecta.domain.conta.organizacao.dto.UpdateOrganizacaoDTO;
import collectiva.org.collecta.domain.conta.organizacao.service.OrganizacaoServiceFacade;
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
    private final OrganizacaoServiceFacade organizacaoServiceF;

    @GetMapping
    public ResponseEntity<List<ResponseOrganizacaoDTO>> buscarOrganizacoes() {
        List<ResponseOrganizacaoDTO> listaDTO = organizacaoServiceF.buscarTodasOrganizacaoes();
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseOrganizacaoDTO> buscarOrganizacaoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(organizacaoServiceF.buscarOrganizacaoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationOrganizacaoDTO> atualizarOrganizacao(@PathVariable UUID id, @RequestBody @Valid UpdateOrganizacaoDTO organizacaoDTO) {
        return ResponseEntity.ok(organizacaoServiceF.atualizarOrganizacao(id, organizacaoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOrganizacao(@PathVariable UUID id) {
        organizacaoServiceF.deletarOrganizacao(id);
        return ResponseEntity.noContent().build();
    }
}
