package collectiva.org.collecta.authentication;

import collectiva.org.collecta.authentication.dto.ContaDetalheDTO;
import collectiva.org.collecta.domain.conta.usuario.Conta;
import collectiva.org.collecta.domain.conta.usuario.repository.ContaRepository;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final ContaRepository contaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws EntidadeNaoEncontradaException {
        Conta conta = contaRepository.findByEmail(username).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Username")
        );
        return new ContaDetalheDTO(conta);
    }
}
