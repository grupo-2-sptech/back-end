package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Doador;
import collectiva.org.collecta.repository.DoadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoadorService {
    private final DoadorRepository doadorRepository;

    public ResponseEntity<Doador> salvarDoador(Doador doador) {
        doadorRepository.save(doador);
        return ResponseEntity.status(HttpStatus.CREATED).body(doador);
    }

    public ResponseEntity<List<Doador>> buscarTodosDoadores() {
        List<Doador> doador = doadorRepository.findAll();
        if (doador.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(doador);
    }

    public ResponseEntity<Optional<Doador>> buscarDoadorPorId(UUID id) {
        Optional<Doador> doador = doadorRepository.findById(id);
        if (doador.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(doador);
    }

    public ResponseEntity<Doador> atualizarDoador(UUID id, Doador doador) {
        Optional<Doador> doadorAntigo = doadorRepository.findById(id);
        if (doadorAntigo.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Doador doadorExistente = doadorAntigo.get();
        Doador doadorAtualizado = Doador.builder()
                .id(doadorExistente.getId())
                .email(doador.getEmail())
                .senha(doador.getSenha())
                .telefone(doador.getTelefone())
                .nome(doador.getNome())
                .sobrenome(doador.getSobrenome())
                .cpf(doador.getCpf())
                .dataNascimento(doador.getDataNascimento())
                .build();

        doadorRepository.save(doadorAtualizado);

        return ResponseEntity.ok().body(doadorAtualizado);
    }

    public ResponseEntity<Void> deletarDoador(UUID id) {
        if (!doadorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        doadorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

