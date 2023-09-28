package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Categoria;
import collectiva.org.collecta.dto.CategoriaDTO;
import collectiva.org.collecta.mapper.CategoriaMapper;
import collectiva.org.collecta.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public ResponseEntity<CategoriaDTO> salvarCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = CategoriaMapper.paraEntidade(categoriaDTO);
        categoriaRepository.save(categoria);
        categoriaDTO = CategoriaMapper.paraDTO(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaDTO);
    }

    public ResponseEntity<List<CategoriaDTO>> buscarTodasCategorias() {
        List<Categoria> categoria = categoriaRepository.findAll();
        if (categoria.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<CategoriaDTO> categoriaDTOs = categoria.stream().map(CategoriaMapper::paraDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(categoriaDTOs);
    }

    public ResponseEntity<CategoriaDTO> buscarCategoriaPorId(UUID id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        CategoriaDTO categoriaDTO = CategoriaMapper.paraDTO(categoria.get());
        return ResponseEntity.ok().body(categoriaDTO);
    }

    public ResponseEntity<CategoriaDTO> atualizarCategoria(UUID id, CategoriaDTO categoriaDTO) {
        Optional<Categoria> categoriaAntiga = categoriaRepository.findById(id);
        if (categoriaAntiga.isPresent()) {
            Categoria categoria = CategoriaMapper.paraEntidade(categoriaDTO);
            categoria.setId(categoriaAntiga.get().getId());
            categoriaDTO.setId(categoriaAntiga.get().getId());

            categoriaRepository.save(categoria);
            return ResponseEntity.ok().body(categoriaDTO);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> deletarCategoria(UUID id) {
        if (!categoriaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoriaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

