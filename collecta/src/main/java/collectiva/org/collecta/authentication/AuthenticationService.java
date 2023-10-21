package collectiva.org.collecta.authentication;

import collectiva.org.collecta.authentication.dto.DoadorDetalheDTO;
import collectiva.org.collecta.domain.Doador;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import collectiva.org.collecta.repository.DoadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final DoadorRepository doadorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Doador doador = doadorRepository.findByEmail(username).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Username")
        );
        return new DoadorDetalheDTO(doador);
    }
}
