package collectiva.org.collecta.domain.conta.doador.service;

import collectiva.org.collecta.domain.conta.doador.Doador;
import collectiva.org.collecta.domain.conta.doador.repository.DoadorRepository;
import collectiva.org.collecta.domain.conta.usuario.service.ContaService;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoadorService {
    private final DoadorRepository doadorRepository;
    private final ContaService contaService;
    private final PasswordEncoder passwordEncoder;

    public Doador salvarDoador(Doador doador) {
        contaService.buscarPorEmail(doador.getEmail());
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
        contaService.buscarPorEmail(doador.getEmail());
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

}

