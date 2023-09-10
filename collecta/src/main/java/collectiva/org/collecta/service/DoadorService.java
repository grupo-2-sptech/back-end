package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Doador;
import collectiva.org.collecta.dto.DoadorDTO;
import collectiva.org.collecta.mapper.DoadorMapper;
import collectiva.org.collecta.repository.DoadorRepository;
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
public class DoadorService {
    private final DoadorRepository doadorRepository;

    public ResponseEntity<DoadorDTO> salvarDoador(DoadorDTO doadorDTO) {
        Doador doador = DoadorMapper.paraEntidade(doadorDTO);
        doadorRepository.save(doador);
        doadorDTO = DoadorMapper.paraDTO(doador);
        return ResponseEntity.status(HttpStatus.CREATED).body(doadorDTO);
    }

    public ResponseEntity<List<DoadorDTO>> buscarTodosDoadores() {
        List<Doador> doadores = doadorRepository.findAll();
        if (doadores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<DoadorDTO> doadoresDTO = doadores.stream().map(DoadorMapper::paraDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(doadoresDTO);
    }

    public ResponseEntity<DoadorDTO> buscarDoadorPorId(UUID id) {
        Optional<Doador> doador = doadorRepository.findById(id);
        if (doador.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        DoadorDTO doadorDTO = DoadorMapper.paraDTO(doador.get());
        return ResponseEntity.ok().body(doadorDTO);
    }

    public ResponseEntity<DoadorDTO> atualizarDoador(UUID id, DoadorDTO doadorDTO) {
        Optional<Doador> doadorAntigo = doadorRepository.findById(id);
        if (doadorAntigo.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Doador doadorExistente = doadorAntigo.get();
        Doador doadorAtualizado = Doador.builder()
                .id(doadorExistente.getId())
                .email(doadorDTO.getEmail())
                .senha(doadorDTO.getSenha())
                .telefone(doadorDTO.getTelefone())
                .nome(doadorDTO.getNome())
                .sobrenome(doadorDTO.getSobrenome())
                .cpf(doadorDTO.getCpf())
                .dataNascimento(doadorDTO.getDataNascimento())
                .build();

        doadorRepository.save(doadorAtualizado);
        doadorDTO = DoadorMapper.paraDTO(doadorAtualizado);
        return ResponseEntity.ok().body(doadorDTO);
    }

    public ResponseEntity<Void> deletarDoador(UUID id) {
        if (!doadorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        doadorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

