package collectiva.org.collecta.domain.conta.doador.mapper;

import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.conta.doador.dto.AssociationDoadorDTO;
import collectiva.org.collecta.domain.conta.doador.dto.CreateDoadorDTO;
import collectiva.org.collecta.domain.conta.doador.dto.ResponseDoadorDTO;
import collectiva.org.collecta.domain.conta.doador.dto.UpdateDoadorDTO;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.ContribuicaoMonetaria;
import collectiva.org.collecta.domain.contribuicao.contribuicaoMonetaria.mapper.ContribuicaoMonetariaMapper;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.ContribuicaoRecurso;
import collectiva.org.collecta.domain.contribuicao.contribuicaoRecurso.mapper.ContribuicaoRecursoMapper;
import collectiva.org.collecta.domain.contribuicao.contribuicaoServico.mapper.ContribuicaoServicoMapper;
import collectiva.org.collecta.domain.plano.mapper.PlanoMapper;
import collectiva.org.collecta.enums.TipoConta;

import java.util.Optional;

public class DoadorMapper {
    private DoadorMapper() {
    }

    public static Doador paraEntidade(CreateDoadorDTO doadorDTO) {
        return Doador.builder()
                .email(doadorDTO.getEmail())
                .senha(doadorDTO.getSenha())
                .tipoConta(TipoConta.DOADOR)
                .telefone(doadorDTO.getTelefone())
                .nome(doadorDTO.getNome())
                .sobrenome(doadorDTO.getSobrenome())
                .dataNascimento(doadorDTO.getDataNascimento())
                .cpf(doadorDTO.getCpf())
                .build();
    }

    public static Doador paraEntidadeUpdate(UpdateDoadorDTO doadorDTO) {
        return Doador.builder()
                .telefone(doadorDTO.getTelefone())
                .nome(doadorDTO.getNome())
                .sobrenome(doadorDTO.getSobrenome())
                .dataNascimento(doadorDTO.getDataNascimento())
                .cpf(doadorDTO.getCpf())
                .build();
    }

    public static ResponseDoadorDTO paraDTO(Doador doador) {
        return ResponseDoadorDTO.builder()
                .id(doador.getId())
                .email(doador.getEmail())
                .telefone(doador.getTelefone())
                .nome(doador.getNome())
                .sobrenome(doador.getSobrenome())
                .cpf(doador.getCpf())
                .doacoesMonetarias(doador.getContribuicaoMonetarias().stream().map(ContribuicaoMonetariaMapper::paraAssociacaoDTO).toList())
                .recursosDoados(doador.getContribuicaoRecursos().stream().map(ContribuicaoRecursoMapper::paraAssociacaoDTO).toList())
                .servicosFeitos(doador.getContribuicaoServico().stream().map(ContribuicaoServicoMapper::paraAssociacaoDTO).toList())
                .plano(Optional.ofNullable(doador.getPlano()).map(PlanoMapper::paraAssociacaoDTO).orElse(null))
                .build();
    }

    public static AssociationDoadorDTO paraAssociacaoDTO(Doador doador) {
        return AssociationDoadorDTO.builder()
                .id(doador.getId())
                .email(doador.getEmail())
                .telefone(doador.getTelefone())
                .nome(doador.getNome())
                .sobrenome(doador.getSobrenome())
                .build();
    }

}
