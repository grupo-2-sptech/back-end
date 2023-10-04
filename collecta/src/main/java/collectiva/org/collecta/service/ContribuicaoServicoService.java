package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.ContribuicaoServico;
import collectiva.org.collecta.dto.ContribuicaoServicoDTO;
import collectiva.org.collecta.mapper.ContribuicaoServicoMapper;
import collectiva.org.collecta.repository.ContribuicaoServicoRepository;
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
public class ContribuicaoServicoService {
    private final ContribuicaoServicoRepository contribuicaoServicoRepository;

    public ResponseEntity<ContribuicaoServicoDTO> salvarContribuicaoServico(ContribuicaoServicoDTO contribuicaoServicoDTO) {
        ContribuicaoServico contribuicaoServico = ContribuicaoServicoMapper.paraEntidade(contribuicaoServicoDTO);
        contribuicaoServicoRepository.save(contribuicaoServico);
        contribuicaoServicoDTO = ContribuicaoServicoMapper.paraDTO(contribuicaoServico);
        return ResponseEntity.status(HttpStatus.CREATED).body(contribuicaoServicoDTO);
    }

    public ResponseEntity<List<ContribuicaoServicoDTO>> buscarTodasContribuicoesServicos() {
        List<ContribuicaoServico> contribuicaoServico = contribuicaoServicoRepository.findAll();
        if (contribuicaoServico.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ContribuicaoServicoDTO> contribuicaoServicoDTOs = contribuicaoServico.stream().map(ContribuicaoServicoMapper::paraDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(contribuicaoServicoDTOs);
    }

    public ResponseEntity<ContribuicaoServicoDTO> buscarContribuicaoServicoPorId(UUID id) {
        Optional<ContribuicaoServico> contribuicaoServico = contribuicaoServicoRepository.findById(id);
        if (contribuicaoServico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ContribuicaoServicoDTO contribuicaoServicoDTO = ContribuicaoServicoMapper.paraDTO(contribuicaoServico.get());
        return ResponseEntity.ok().body(contribuicaoServicoDTO);
    }

    public ResponseEntity<ContribuicaoServicoDTO> atualizarContribuicaoServico(UUID id, ContribuicaoServicoDTO contribuicaoServicoDTO) {
        Optional<ContribuicaoServico> contribuicaoServicoAntiga = contribuicaoServicoRepository.findById(id);
        if (contribuicaoServicoAntiga.isPresent()) {
            ContribuicaoServico contribuicaoServico = ContribuicaoServicoMapper.paraEntidade(contribuicaoServicoDTO);
            contribuicaoServico.setId(contribuicaoServicoAntiga.get().getId());
            contribuicaoServicoDTO.setId(contribuicaoServicoAntiga.get().getId());

            contribuicaoServicoRepository.save(contribuicaoServico);
            return ResponseEntity.ok().body(contribuicaoServicoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> deletarContribuicaoServico(UUID id) {
        if (!contribuicaoServicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        contribuicaoServicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

