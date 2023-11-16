package collectiva.org.collecta.domain.conta.Doador.service;

import collectiva.org.collecta.authentication.dto.DoadorLoginDTO;
import collectiva.org.collecta.authentication.dto.DoadorTokenDTO;
import collectiva.org.collecta.authentication.jwt.GerenciadorTokenJwt;
import collectiva.org.collecta.domain.conta.Doador.Doador;
import collectiva.org.collecta.domain.conta.Doador.mapper.DoadorMapper;
import collectiva.org.collecta.domain.conta.Doador.repository.DoadorRepository;
import collectiva.org.collecta.exception.exceptions.ConflitoEntidadeException;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoadorService {
    private final DoadorRepository doadorRepository;
    private final PasswordEncoder passwordEncoder;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;

    public Doador salvarDoador(Doador doador) {
        buscarPorEmail(doador.getEmail());
        String senhaCriptografada = passwordEncoder.encode(doador.getSenha());
        doador.setSenha(senhaCriptografada);
        return doadorRepository.save(doador);
    }

    public List<Doador> buscarTodosDoadores() {
        return doadorRepository.findAll();
    }

    public Doador buscarDoadorPorId(UUID id) {
        return doadorRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Doador"));
    }

    public Doador atualizarDoador(UUID id, Doador doador) {
        buscarPorEmail(doador.getEmail());
        buscarDoadorPorId(id);
        doador.setId(id);
        return doadorRepository.save(doador);
    }

    public void deletarDoador(UUID id) {
        if (!doadorRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Doador");
        }
        doadorRepository.deleteById(id);
    }

    public void buscarPorEmail(String email) {
        if (doadorRepository.existsByEmail(email)) {
            throw new ConflitoEntidadeException("Email");
        }
    }

    public DoadorTokenDTO autenticar(DoadorLoginDTO usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Doador doadorAutenticado =
                doadorRepository.findByEmail(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new EntidadeNaoEncontradaException("Email")
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return DoadorMapper.paraToken(doadorAutenticado, token);
    }
}

