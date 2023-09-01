package collectiva.org.collecta.controller;

import collectiva.org.collecta.domain.Organizacao;
import collectiva.org.collecta.service.OrganizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/organizacao")
public class OrganizacaoController {

    @Autowired
    private OrganizacaoService organizacaoService;

    @GetMapping
    public ResponseEntity<List<Organizacao>> buscarOrganizacoes() {
        return organizacaoService.buscarTodasOrganizacoes();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Organizacao> buscarOrganizacaoPorId(@PathVariable UUID id) {
        return organizacaoService.buscarOrganizacaoPorId(id);
    }

    @PostMapping
    public ResponseEntity<Organizacao> criarOrganizacao(@RequestBody Organizacao organizacao) {
        return organizacaoService.salvarOrganizacao(organizacao);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Organizacao> atualizarOrganizacao(@PathVariable UUID id, @RequestBody Organizacao organizacao) {
        return organizacaoService.atualizarOrganizacao(id, organizacao);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOrganizacao(@PathVariable UUID id){
        return  organizacaoService.deletarOrganizacao(id);
    }
}
