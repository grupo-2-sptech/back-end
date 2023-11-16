package collectiva.org.collecta.domain.endereco.service;

import collectiva.org.collecta.domain.endereco.Endereco;
import collectiva.org.collecta.domain.endereco.repository.EnderecoRepository;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    public Endereco salvarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> buscarTodosEnderecos() {
        return enderecoRepository.findAll();
    }

    public Endereco buscarEnderecoPorId(UUID id) {
        return enderecoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Endereco"));
    }

    public Endereco atualizarEndereco(UUID id, Endereco endereco) {
        buscarEnderecoPorId(id);
        endereco.setId(id);
        return enderecoRepository.save(endereco);
    }

    public void deletarEndereco(UUID id) {
        if (!enderecoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Endereco");
        }
        enderecoRepository.deleteById(id);
    }
}

