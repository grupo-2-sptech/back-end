package collectiva.org.collecta.mapper;

import collectiva.org.collecta.authentication.dto.DoadorTokenDTO;
import collectiva.org.collecta.domain.Doador;
import collectiva.org.collecta.dto.DoadorDTO;

public class DoadorMapper {
    private DoadorMapper() {
    }

    public static Doador paraEntidade(DoadorDTO doadorDTO){
        return Doador.builder()
                .email(doadorDTO.getEmail())
                .senha(doadorDTO.getSenha())
                .telefone(doadorDTO.getTelefone())
                .nome(doadorDTO.getNome())
                .sobrenome(doadorDTO.getSobrenome())
                .dataNascimento(doadorDTO.getDataNascimento())
                .cpf(doadorDTO.getCpf())
                .build();
    }

    public static DoadorDTO paraDTO(Doador doador){
        return DoadorDTO.builder()
                .id(doador.getId())
                .email(doador.getEmail())
                .telefone(doador.getTelefone())
                .nome(doador.getNome())
                .sobrenome(doador.getSobrenome())
                .build();
    }

    public static DoadorTokenDTO paraToken(Doador doador, String token) {
        return DoadorTokenDTO.builder()
                .id(doador.getId())
                .email(doador.getEmail())
                .nome(doador.getNome())
                .token(token)
                .build();
    }
}
