package collectiva.org.collecta.domain.campanha.controller;

import collectiva.org.collecta.domain.campanha.Campanha;
import collectiva.org.collecta.domain.campanha.dto.AssociationCampanhaDTO;
import collectiva.org.collecta.domain.campanha.dto.CreateCampanhaDTO;
import collectiva.org.collecta.domain.campanha.dto.ResponseCampanhaDTO;
import collectiva.org.collecta.domain.campanha.dto.UpdateCampanhaDTO;
import collectiva.org.collecta.domain.campanha.mapper.CampanhaMapper;
import collectiva.org.collecta.domain.campanha.service.CampanhaService;
import collectiva.org.collecta.domain.conta.organizacao.Organizacao;
import collectiva.org.collecta.domain.conta.organizacao.service.OrganizacaoService;
import collectiva.org.collecta.enums.CategoriaCampanha;
import collectiva.org.collecta.enums.TipoCampanha;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/campanhas")
@RequiredArgsConstructor
public class CampanhaController {
    private final CampanhaService campanhaService;
    private final OrganizacaoService organizacaoService;

    @GetMapping
    public ResponseEntity<List<ResponseCampanhaDTO>> buscarCampanhas() {
        List<ResponseCampanhaDTO> listaDTO = campanhaService.buscarTodasCampanhas().stream().map
                (CampanhaMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }
    @GetMapping("/top3")
    public ResponseEntity<List<ResponseCampanhaDTO>> buscarTop3CampanhasPorTipo(@RequestParam TipoCampanha tipoCampanha) {
        List<ResponseCampanhaDTO> listaDTO = campanhaService.buscarTop3CampanhasPorTipo(tipoCampanha).stream().map
                (CampanhaMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/genero/{categoriaCampanha}")
    public ResponseEntity<List<ResponseCampanhaDTO>> buscarCampanhaPorGenero(@PathVariable CategoriaCampanha categoriaCampanha) {
        List<ResponseCampanhaDTO> listaDTO = campanhaService.buscarCampanhasPorGenero(categoriaCampanha).stream().map
                (CampanhaMapper::paraDTO).toList();
        return ResponseEntity.status(listaDTO.isEmpty() ? 204 : 200).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCampanhaDTO> buscarCampanhaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(CampanhaMapper.paraDTO(campanhaService.buscarCampanhaPorId(id)));
    }

    @PostMapping
    public ResponseEntity<AssociationCampanhaDTO> criarCampanha(@RequestBody @Valid CreateCampanhaDTO campanhaDTO) {
        Organizacao organizacao = organizacaoService.buscarOrganizacaoPorId(campanhaDTO.getIdOrganizacao());
        Campanha campanha = campanhaService.salvarCampanha(CampanhaMapper.paraEntidade(campanhaDTO), organizacao);
        return ResponseEntity.status(201).body(CampanhaMapper.paraAssociacaoDTO(campanha));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationCampanhaDTO> atualizarCampanha(@PathVariable UUID id, @Valid @RequestBody UpdateCampanhaDTO campanhaDTO) {
        Campanha novaCampanha = campanhaService.atualizarCampanha(id, CampanhaMapper.paraEntidadeUpdate(campanhaDTO));
        return ResponseEntity.ok(CampanhaMapper.paraAssociacaoDTO(novaCampanha));
    }

    @PutMapping("/mudar-status/{id}")
    public ResponseEntity<Void> atualizarStatus(@PathVariable UUID id) {
        Campanha campanha = campanhaService.atualizarStatus(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCampanha(@PathVariable UUID id) {
        campanhaService.deletarCampanha(id);
        return ResponseEntity.noContent().build();
    }
}
