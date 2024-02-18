package collectiva.org.collecta.domain.campanha.controller;

import collectiva.org.collecta.domain.campanha.dto.AssociationCampanhaDTO;
import collectiva.org.collecta.domain.campanha.dto.CreateCampanhaDTO;
import collectiva.org.collecta.domain.campanha.dto.ResponseCampanhaDTO;
import collectiva.org.collecta.domain.campanha.dto.UpdateCampanhaDTO;
import collectiva.org.collecta.domain.campanha.service.CampanhaService;
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

    @GetMapping
    public ResponseEntity<List<ResponseCampanhaDTO>> buscarCampanhas() {
        List<ResponseCampanhaDTO> listaDTO = campanhaService.buscarTodasCampanhas();
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/top3")
    public ResponseEntity<List<ResponseCampanhaDTO>> buscarTop3CampanhasPorTipo(@RequestParam TipoCampanha tipoCampanha) {
        List<ResponseCampanhaDTO> listaDTO = campanhaService.buscarTop3CampanhasPorTipo(tipoCampanha);
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/genero/{categoriaCampanha}")
    public ResponseEntity<List<ResponseCampanhaDTO>> buscarCampanhaPorGenero(@PathVariable CategoriaCampanha categoriaCampanha) {
        List<ResponseCampanhaDTO> listaDTO = campanhaService.buscarCampanhasPorGenero(categoriaCampanha);
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCampanhaDTO> buscarCampanhaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(campanhaService.buscarCampanhaPorId(id));
    }

    @PostMapping("/{idOrganizacao}")
    public ResponseEntity<AssociationCampanhaDTO> criarCampanha(@PathVariable UUID idOrganizacao, @RequestBody @Valid CreateCampanhaDTO campanhaDTO) {
        return ResponseEntity.status(201).body(campanhaService.criarCampanha(campanhaDTO, idOrganizacao));
    }

    @PutMapping("/{idOrganizacao}")
    public ResponseEntity<AssociationCampanhaDTO> atualizarCampanha(@PathVariable UUID idOrganizacao, @Valid @RequestBody UpdateCampanhaDTO campanhaDTO) {
        return ResponseEntity.ok(campanhaService.atualizarCampanha(idOrganizacao, campanhaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCampanha(@PathVariable UUID id) {
        campanhaService.deletarCampanha(id);
        return ResponseEntity.noContent().build();
    }
}
