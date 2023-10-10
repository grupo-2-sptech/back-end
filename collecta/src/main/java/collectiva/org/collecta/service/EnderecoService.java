package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Endereco;
import collectiva.org.collecta.dto.EnderecoDTO;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoContemElementosException;
import collectiva.org.collecta.exception.exceptions.EntidadeNaoEncontradaException;
import collectiva.org.collecta.mapper.EnderecoMapper;
import collectiva.org.collecta.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    public EnderecoDTO salvarEndereco(EnderecoDTO enderecoDTO) {
        Endereco endereco = EnderecoMapper.paraEntidade(enderecoDTO);
        enderecoRepository.save(endereco);
        return EnderecoMapper.paraDTO(endereco);
    }

    public List<EnderecoDTO> buscarTodosEnderecos() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        if (enderecos.isEmpty()) {
            throw new EntidadeNaoContemElementosException("Endereco");
        }
        return enderecos.stream().map(EnderecoMapper::paraDTO).collect(Collectors.toList());
    }

    public EnderecoDTO buscarEnderecoPorId(UUID id) {
        return EnderecoMapper.paraDTO(enderecoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Endereco")));
    }

    public EnderecoDTO atualizarEndereco(UUID id, EnderecoDTO enderecoDTO) {
        buscarEnderecoPorId(id);
        Endereco enderecoNovo = EnderecoMapper.paraEntidade(enderecoDTO);
        enderecoNovo.setId(id);
        enderecoRepository.save(enderecoNovo);
        return EnderecoMapper.paraDTO(enderecoNovo);
    }

    public void deletarEndereco(UUID id) {
        if (!enderecoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Endereco");
        }
        enderecoRepository.deleteById(id);
    }
}

