package collectiva.org.collecta.domain.conta.doador.mapper;

import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.conta.doador.dto.CreateDoadorDTO;
import collectiva.org.collecta.domain.conta.doador.dto.ResponseDoadorDTO;
import collectiva.org.collecta.domain.conta.doador.dto.UpdateDoadorDTO;
import collectiva.org.collecta.enums.TipoConta;

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
                .build();
    }

}
