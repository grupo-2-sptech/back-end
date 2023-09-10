package collectiva.org.collecta.controller;

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
        return organizacaoService.buscarTodasOrganizacoes();
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrganizacaoDTO> buscarOrganizacaoPorId(@PathVariable UUID id) {
        return organizacaoService.buscarOrganizacaoPorId(id);
    }

    @PostMapping
    public ResponseEntity<OrganizacaoDTO> criarOrganizacao(@RequestBody @Valid OrganizacaoDTO organizacaoDTO) {
        return organizacaoService.salvarOrganizacao(organizacaoDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrganizacaoDTO> atualizarOrganizacao(@PathVariable UUID id, @RequestBody @Valid OrganizacaoDTO organizacaoDTO) {
        return organizacaoService.atualizarOrganizacao(id, organizacaoDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOrganizacao(@PathVariable UUID id){
        return  organizacaoService.deletarOrganizacao(id);
    }
}
