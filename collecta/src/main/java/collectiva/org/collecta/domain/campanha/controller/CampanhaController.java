package collectiva.org.collecta.domain.campanha.controller;

import collectiva.org.collecta.domain.campanha.dto.AssociationCampanhaDTO;
import collectiva.org.collecta.domain.campanha.dto.CreateCampanhaDTO;
import collectiva.org.collecta.domain.campanha.dto.ResponseCampanhaDTO;
import collectiva.org.collecta.domain.campanha.dto.UpdateCampanhaDTO;
import collectiva.org.collecta.domain.campanha.service.CampanhaServiceFacade;
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
    private final CampanhaServiceFacade campanhaServiceF;

    @GetMapping
    public ResponseEntity<List<ResponseCampanhaDTO>> buscarCampanhas() {
        List<ResponseCampanhaDTO> listaDTO = campanhaServiceF.buscarTodasCampanhas();
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/top3")
    public ResponseEntity<List<ResponseCampanhaDTO>> buscarTop3CampanhasPorTipo(@RequestParam TipoCampanha tipoCampanha) {
        List<ResponseCampanhaDTO> listaDTO = campanhaServiceF.buscarTop3CampanhasPorTipo(tipoCampanha);
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/genero/{categoriaCampanha}")
    public ResponseEntity<List<ResponseCampanhaDTO>> buscarCampanhaPorGenero(@PathVariable CategoriaCampanha categoriaCampanha) {
        List<ResponseCampanhaDTO> listaDTO = campanhaServiceF.buscarCampanhasPorGenero(categoriaCampanha);
        return listaDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCampanhaDTO> buscarCampanhaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(campanhaServiceF.buscarCampanhaPorId(id));
    }

    @PostMapping("/{idOrganizacao}")
    public ResponseEntity<AssociationCampanhaDTO> criarCampanha(@PathVariable UUID idOrganizacao, @RequestBody @Valid CreateCampanhaDTO campanhaDTO) {
        return ResponseEntity.status(201).body(campanhaServiceF.criarCampanha(idOrganizacao, campanhaDTO));
    }

    @PutMapping("/{idOrganizacao}")
    public ResponseEntity<AssociationCampanhaDTO> atualizarCampanha(@PathVariable UUID idOrganizacao, @Valid @RequestBody UpdateCampanhaDTO campanhaDTO) {
        return ResponseEntity.ok(campanhaServiceF.atualizarCampanha(idOrganizacao, campanhaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCampanha(@PathVariable UUID id) {
        campanhaServiceF.deletarCampanha(id);
        return ResponseEntity.noContent().build();
    }
}
