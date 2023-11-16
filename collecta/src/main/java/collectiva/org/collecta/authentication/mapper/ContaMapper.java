package collectiva.org.collecta.authentication.mapper;

import collectiva.org.collecta.authentication.dto.ContaTokenDTO;
import collectiva.org.collecta.domain.conta.usuario.Conta;

public class ContaMapper {

    private ContaMapper() {
    }

    public static ContaTokenDTO paraToken(Conta conta, String token) {
        return ContaTokenDTO.builder()
                .id(conta.getId())
                .email(conta.getEmail())
                .tipoConta(conta.getTipoConta())
                .token(token)
                .build();
    }
}
