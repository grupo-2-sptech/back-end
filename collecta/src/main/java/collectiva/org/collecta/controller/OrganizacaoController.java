package collectiva.org.collecta.controller;

import collectiva.org.collecta.dto.CampanhaDTO;
import collectiva.org.collecta.dto.OrganizacaoDTO;
import collectiva.org.collecta.service.OrganizacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/organizacoes")
public class OrganizacaoController {

    @Autowired
    private OrganizacaoService organizacaoService;

    @GetMapping
    public ResponseEntity<List<OrganizacaoDTO>> buscarOrganizacoes() {
        List<OrganizacaoDTO> lista = organizacaoService.buscarTodasOrganizacoes();
        return ResponseEntity.status(lista.isEmpty()? 204 : 200).body(lista);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrganizacaoDTO> buscarOrganizacaoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(organizacaoService.buscarOrganizacaoPorId(id));
    }

    @PostMapping
    public ResponseEntity<OrganizacaoDTO> criarOrganizacao(@RequestBody @Valid OrganizacaoDTO organizacaoDTO) {
        return ResponseEntity.status(201).body(organizacaoService.salvarOrganizacao(organizacaoDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrganizacaoDTO> atualizarOrganizacao(@PathVariable UUID id, @RequestBody @Valid OrganizacaoDTO organizacaoDTO) {
        return ResponseEntity.ok(organizacaoService.atualizarOrganizacao(id, organizacaoDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOrganizacao(@PathVariable UUID id){
        organizacaoService.deletarOrganizacao(id);
        return ResponseEntity.noContent().build();
    }
}
