package collectiva.org.collecta.service;

import collectiva.org.collecta.authentication.dto.DoadorLoginDTO;
import collectiva.org.collecta.authentication.dto.DoadorTokenDTO;
import collectiva.org.collecta.authentication.jwt.GerenciadorTokenJwt;
import collectiva.org.collecta.domain.Doador;
import collectiva.org.collecta.dto.DoadorDTO;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import collectiva.org.collecta.mapper.DoadorMapper;
import collectiva.org.collecta.repository.DoadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoadorService {
    private final DoadorRepository doadorRepository;
    private final PasswordEncoder passwordEncoder;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;

    public DoadorTokenDTO salvarDoador(DoadorDTO doadorDTO) {
        Doador doador = DoadorMapper.paraEntidade(doadorDTO);
        String senhaCriptografada = passwordEncoder.encode(doador.getSenha());
        doador.setSenha(senhaCriptografada);
        doadorRepository.save(doador);
        return DoadorMapper.paraToken(doador, senhaCriptografada);
    }

    public List<DoadorDTO> buscarTodosDoadores() {
        List<Doador> doadores = doadorRepository.findAll();
        return doadores.stream().map(DoadorMapper::paraDTO).collect(Collectors.toList());
    }

    public DoadorDTO buscarDoadorPorId(UUID id) {
        return DoadorMapper.paraDTO(doadorRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Doador")));
    }

    public DoadorDTO atualizarDoador(UUID id, DoadorDTO doadorDTO) {
        buscarDoadorPorId(id);
        Doador doadorNovo = DoadorMapper.paraEntidade(doadorDTO);
        doadorNovo.setId(id);
        doadorRepository.save(doadorNovo);
        return DoadorMapper.paraDTO(doadorNovo);
    }

    public void deletarDoador(UUID id) {
        if (!doadorRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Doador");
        }
        doadorRepository.deleteById(id);
    }

    public DoadorTokenDTO autenticar(DoadorLoginDTO usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Doador doadorAutenticado =
                doadorRepository.findByEmail(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return DoadorMapper.paraToken(doadorAutenticado, token);
    }
}

