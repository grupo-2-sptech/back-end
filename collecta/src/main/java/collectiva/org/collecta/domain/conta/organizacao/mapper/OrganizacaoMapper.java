package collectiva.org.collecta.domain.conta.organizacao.mapper;

import collectiva.org.collecta.domain.campanha.mapper.CampanhaMapper;
import collectiva.org.collecta.domain.conta.organizacao.Organizacao;
import collectiva.org.collecta.domain.conta.organizacao.dto.AssociationOrganizacaoDTO;
import collectiva.org.collecta.domain.conta.organizacao.dto.CreateOrganizacaoDTO;
import collectiva.org.collecta.domain.conta.organizacao.dto.ResponseOrganizacaoDTO;
import collectiva.org.collecta.domain.conta.organizacao.dto.UpdateOrganizacaoDTO;
import collectiva.org.collecta.enums.TipoConta;

public class OrganizacaoMapper {
    private OrganizacaoMapper() {
    }

    public static Organizacao paraEntidade(CreateOrganizacaoDTO organizacaoDTO){
        return Organizacao.builder()
                .email(organizacaoDTO.getEmail())
                .senha(organizacaoDTO.getSenha())
                .tipoConta(TipoConta.ORGANIZACAO)
                .telefone(organizacaoDTO.getTelefone())
                .nomeSocial(organizacaoDTO.getNomeSocial())
                .nomeFantasia(organizacaoDTO.getNomeFantasia())
                .dataFundacao(organizacaoDTO.getDataFundacao())
                .cnpj(organizacaoDTO.getCnpj())
                .build();
    }

    public static Organizacao paraEntidadeUpdate(UpdateOrganizacaoDTO organizacaoDTO){
        return Organizacao.builder()
                .telefone(organizacaoDTO.getTelefone())
                .nomeSocial(organizacaoDTO.getNomeSocial())
                .nomeFantasia(organizacaoDTO.getNomeFantasia())
                .dataFundacao(organizacaoDTO.getDataFundacao())
                .cnpj(organizacaoDTO.getCnpj())
                .build();
    }

    public static ResponseOrganizacaoDTO paraDTO(Organizacao organizacao){
        return ResponseOrganizacaoDTO.builder()
                .id(organizacao.getId())
                .email(organizacao.getEmail())
                .telefone(organizacao.getTelefone())
                .nomeSocial(organizacao.getNomeSocial())
                .nomeFantasia(organizacao.getNomeFantasia())
                .campanhas(organizacao.getCampanhas().stream().map(CampanhaMapper::paraAssociacaoDTO).toList())
                .build();
    }

    public static AssociationOrganizacaoDTO paraAssociacaoDTO(Organizacao organizacao){
        return AssociationOrganizacaoDTO.builder()
                .id(organizacao.getId())
                .email(organizacao.getEmail())
                .telefone(organizacao.getTelefone())
                .nomeSocial(organizacao.getNomeSocial())
                .nomeFantasia(organizacao.getNomeFantasia())
                .build();
    }
}
