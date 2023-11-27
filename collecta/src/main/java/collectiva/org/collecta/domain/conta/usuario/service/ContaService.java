package collectiva.org.collecta.domain.conta.usuario.service;

import collectiva.org.collecta.authentication.dto.ContaLoginDTO;
import collectiva.org.collecta.authentication.dto.ContaTokenDTO;
import collectiva.org.collecta.authentication.jwt.GerenciadorTokenJwt;
import collectiva.org.collecta.authentication.mapper.ContaMapper;
import collectiva.org.collecta.domain.conta.usuario.Conta;
import collectiva.org.collecta.domain.conta.usuario.repository.ContaRepository;
import collectiva.org.collecta.exception.exceptions.ConflitoEntidadeException;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ContaService {
    private final ContaRepository contaRepository;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;

    public ContaTokenDTO autenticar(ContaLoginDTO usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Conta contaAutenticado =
                contaRepository.findByEmail(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new EntidadeNaoEncontradaException("Email")
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return ContaMapper.paraToken(contaAutenticado, token);
    }

    public void buscarPorEmail(String email) {
        if (contaRepository.existsByEmail(email)) {
            throw new ConflitoEntidadeException("Email");
        }
    }
}
