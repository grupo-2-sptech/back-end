package collectiva.org.collecta.mapper;

import collectiva.org.collecta.domain.Categoria;
import collectiva.org.collecta.dto.CategoriaDTO;

public class CategoriaMapper {
    private CategoriaMapper() {
    }

    public static Categoria paraEntidade(CategoriaDTO categoriaDTO){
        return Categoria.builder()
                .nome(categoriaDTO.getNome())
                .descricao(categoriaDTO.getDescricao())
                .build();
    }

    public static CategoriaDTO paraDTO(Categoria categoria){
        return CategoriaDTO.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .descricao(categoria.getDescricao())
                .build();
    }

}
