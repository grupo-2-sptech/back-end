package collectiva.org.collecta.domain.endereco.mapper;

import collectiva.org.collecta.domain.endereco.Endereco;
import collectiva.org.collecta.domain.endereco.dto.AssociationEnderecoDTO;
import collectiva.org.collecta.domain.endereco.dto.CreateEnderecoDTO;
import collectiva.org.collecta.domain.endereco.dto.ResponseEnderecoDTO;
import collectiva.org.collecta.domain.endereco.dto.UpdateEnderecoDTO;
import collectiva.org.collecta.domain.eventoCampanha.mapper.EventoCampanhaMapper;

public class EnderecoMapper {
    private EnderecoMapper() {
    }

    public static Endereco paraEntidade(CreateEnderecoDTO enderecoDTO){
        return Endereco.builder()
                .logradouro(enderecoDTO.getLogradouro())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .cep(enderecoDTO.getCep())
                .numero(enderecoDTO.getNumero())
                .build();
    }
    public static Endereco paraEntidadeUpdate(UpdateEnderecoDTO enderecoDTO){
        return Endereco.builder()
                .logradouro(enderecoDTO.getLogradouro())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .cep(enderecoDTO.getCep())
                .numero(enderecoDTO.getNumero())
                .build();
    }

    public static ResponseEnderecoDTO paraDTO(Endereco endereco){
        return ResponseEnderecoDTO.builder()
                .id(endereco.getId())
                .logradouro(endereco.getLogradouro())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .cep(endereco.getCep())
                .numero(endereco.getNumero())
                .eventoCampanha(EventoCampanhaMapper.paraAssociacaoDTO(endereco.getEventoCampanha()))
                .build();
    }

    public static AssociationEnderecoDTO paraAssociacaoDTO(Endereco endereco){
        return AssociationEnderecoDTO.builder()
                .id(endereco.getId())
                .logradouro(endereco.getLogradouro())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .cep(endereco.getCep())
                .numero(endereco.getNumero())
                .build();
    }
}
