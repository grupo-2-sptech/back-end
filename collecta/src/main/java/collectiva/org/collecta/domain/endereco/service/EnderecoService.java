package collectiva.org.collecta.domain.endereco.service;

import collectiva.org.collecta.domain.endereco.Endereco;
import collectiva.org.collecta.domain.endereco.repository.EnderecoRepository;
import collectiva.org.collecta.domain.eventoCampanha.EventoCampanha;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    public List<Endereco> buscarTodosEnderecos() {
        return enderecoRepository.findAll();
    }

    public Endereco buscarEnderecoPorId(UUID id) {
        return enderecoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Endereco"));
    }

    public Endereco criarEndereco(Endereco endereco, EventoCampanha eventoCampanha) {
        endereco.setEventoCampanha(eventoCampanha);
        return enderecoRepository.save(endereco);
    }

    public Endereco atualizarEndereco(UUID id, Endereco endereco) {
        Endereco enderecoAntigo = buscarEnderecoPorId(id);
        endereco.setId(id);
        endereco.setEventoCampanha(enderecoAntigo.getEventoCampanha());
        return enderecoRepository.save(endereco);
    }

    public void deletarEndereco(UUID id) {
        buscarEnderecoPorId(id);
        enderecoRepository.deleteById(id);
    }
}

