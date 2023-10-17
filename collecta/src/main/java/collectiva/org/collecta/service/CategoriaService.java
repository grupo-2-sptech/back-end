package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Categoria;
import collectiva.org.collecta.dto.CategoriaDTO;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import collectiva.org.collecta.mapper.CategoriaMapper;
import collectiva.org.collecta.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaDTO salvarCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = CategoriaMapper.paraEntidade(categoriaDTO);
        categoriaRepository.save(categoria);
        return CategoriaMapper.paraDTO(categoria);
    }

    public List<CategoriaDTO> buscarTodasCategorias() {
        List<Categoria> categoria = categoriaRepository.findAll();
        return categoria.stream().map(CategoriaMapper::paraDTO).collect(Collectors.toList());
    }

    public CategoriaDTO buscarCategoriaPorId(UUID id) {
        return CategoriaMapper.paraDTO(categoriaRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Categoria")));
    }

    public CategoriaDTO atualizarCategoria(UUID id, CategoriaDTO categoriaDTO) {
        buscarCategoriaPorId(id);
        Categoria categoriaNova = CategoriaMapper.paraEntidade(categoriaDTO);
        categoriaNova.setId(id);
        categoriaRepository.save(categoriaNova);
        return CategoriaMapper.paraDTO(categoriaNova);
    }

    public void deletarCategoria(UUID id) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Categoria");
        }
        categoriaRepository.deleteById(id);
    }
}

