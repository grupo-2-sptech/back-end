package collectiva.org.collecta.domain.conta.Organizacao.mapper;

import collectiva.org.collecta.domain.conta.Organizacao.Organizacao;
import collectiva.org.collecta.domain.conta.Organizacao.dto.OrganizacaoDTO;

public class OrganizacaoMapper {
    private OrganizacaoMapper() {
    }

    public static Organizacao paraEntidade(OrganizacaoDTO organizacaoDTO){
        return Organizacao.builder()
                .email(organizacaoDTO.getEmail())
                .senha(organizacaoDTO.getSenha())
                .telefone(organizacaoDTO.getTelefone())
                .nomeSocial(organizacaoDTO.getNomeSocial())
                .nomeFantasia(organizacaoDTO.getNomeFantasia())
                .dataFundacao(organizacaoDTO.getDataFundacao())
                .cnpj(organizacaoDTO.getCnpj())
                .build();
    }

    public static OrganizacaoDTO paraDTO(Organizacao organizacao){
        return OrganizacaoDTO.builder()
                .id(organizacao.getId())
                .email(organizacao.getEmail())
                .telefone(organizacao.getTelefone())
                .nomeSocial(organizacao.getNomeSocial())
                .nomeFantasia(organizacao.getNomeFantasia())
                .build();
    }
}