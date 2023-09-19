package collectiva.org.collecta.mapper;

import collectiva.org.collecta.domain.Endereco;
import collectiva.org.collecta.dto.EnderecoDTO;

public class EnderecoMapper {
    private EnderecoMapper() {
    }

    public static Endereco paraEntidade(EnderecoDTO enderecoDTO){
        return Endereco.builder()
                .logradouro(enderecoDTO.getLogradouro())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .cep(enderecoDTO.getCep())
                .numero(enderecoDTO.getNumero())
                .build();
    }

    public static EnderecoDTO paraDTO(Endereco endereco){
        return EnderecoDTO.builder()
                .id(endereco.getId())
                .logradouro(endereco.getLogradouro())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .cep(endereco.getCep())
                .numero(endereco.getNumero())
                .build();
    }
}
