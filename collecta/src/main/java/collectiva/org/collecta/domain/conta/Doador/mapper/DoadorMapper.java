package collectiva.org.collecta.domain.conta.Doador.mapper;

import collectiva.org.collecta.authentication.dto.DoadorLoginDTO;
import collectiva.org.collecta.authentication.dto.DoadorTokenDTO;
import collectiva.org.collecta.domain.conta.Doador.Doador;
import collectiva.org.collecta.domain.conta.Doador.dto.DoadorDTO;

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
    public static DoadorLoginDTO paraLogin(Doador doador) {
        return DoadorLoginDTO.builder()
                .email(doador.getEmail())
                .senha(doador.getSenha())
                .build();
    }
}
