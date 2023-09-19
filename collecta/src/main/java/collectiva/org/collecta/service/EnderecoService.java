package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Endereco;
import collectiva.org.collecta.dto.EnderecoDTO;
import collectiva.org.collecta.mapper.EnderecoMapper;
import collectiva.org.collecta.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    public ResponseEntity<EnderecoDTO> salvarEndereco(EnderecoDTO enderecoDTO) {
        Endereco endereco = EnderecoMapper.paraEntidade(enderecoDTO);
        enderecoRepository.save(endereco);
        enderecoDTO = EnderecoMapper.paraDTO(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoDTO);
    }

    public ResponseEntity<List<EnderecoDTO>> buscarTodosEnderecos() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        if (enderecos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<EnderecoDTO> enderecosDTO = enderecos.stream().map(EnderecoMapper::paraDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(enderecosDTO);
    }

    public ResponseEntity<EnderecoDTO> buscarEnderecoPorId(UUID id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        if (endereco.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        EnderecoDTO enderecoDTO = EnderecoMapper.paraDTO(endereco.get());
        return ResponseEntity.ok().body(enderecoDTO);
    }

    public ResponseEntity<EnderecoDTO> atualizarEndereco(UUID id, EnderecoDTO enderecoDTO) {
        Optional<Endereco> enderecoAntigo = enderecoRepository.findById(id);
        if (enderecoAntigo.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        Endereco enderecoExistente = enderecoAntigo.get();
        Endereco enderecoAtualizado = Endereco.builder()
                .id(enderecoExistente.getId())
                .logradouro(enderecoDTO.getLogradouro())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .cep(enderecoDTO.getCep())
                .numero(enderecoDTO.getNumero())
                .build();

        enderecoRepository.save(enderecoAtualizado);
        enderecoDTO = EnderecoMapper.paraDTO(enderecoAtualizado);
        return ResponseEntity.ok().body(enderecoDTO);
    }

    public ResponseEntity<Void> deletarEndereco(UUID id) {
        if (!enderecoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        enderecoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

