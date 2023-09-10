package collectiva.org.collecta.service;

import collectiva.org.collecta.domain.Organizacao;
import collectiva.org.collecta.dto.OrganizacaoDTO;
import collectiva.org.collecta.mapper.OrganizacaoMapper;
import collectiva.org.collecta.repository.OrganizacaoRepository;
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
public class OrganizacaoService {
    private final OrganizacaoRepository organizacaoRepository;

    public ResponseEntity<OrganizacaoDTO> salvarOrganizacao(OrganizacaoDTO organizacaoDTO) {
        Organizacao organizacao = OrganizacaoMapper.paraEntidade(organizacaoDTO);
        organizacaoRepository.save(organizacao);
        organizacaoDTO = OrganizacaoMapper.paraDTO(organizacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(organizacaoDTO);
    }

    public ResponseEntity<List<OrganizacaoDTO>> buscarTodasOrganizacoes() {
        List<Organizacao> organizacoes = organizacaoRepository.findAll();
        if (organizacoes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<OrganizacaoDTO> organizacoesDTO = organizacoes.stream().map(OrganizacaoMapper::paraDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(organizacoesDTO);
    }

    public ResponseEntity<OrganizacaoDTO> buscarOrganizacaoPorId(UUID id) {
        Optional<Organizacao> organizacao = organizacaoRepository.findById(id);
        if (organizacao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        OrganizacaoDTO organizacaoDTO = OrganizacaoMapper.paraDTO(organizacao.get());
        return ResponseEntity.ok().body(organizacaoDTO);
    }

    public ResponseEntity<OrganizacaoDTO> atualizarOrganizacao(UUID id, OrganizacaoDTO organizacaoDTO) {
        Optional<Organizacao> organizacaoAntiga = organizacaoRepository.findById(id);
        if (organizacaoAntiga.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Organizacao organizacaoExistente = organizacaoAntiga.get();
        Organizacao organizacaoAtualizada = Organizacao.builder()
                .id(organizacaoExistente.getId())
                .email(organizacaoDTO.getEmail())
                .senha(organizacaoDTO.getSenha())
                .telefone(organizacaoDTO.getTelefone())
                .nomeFantasia(organizacaoDTO.getNomeFantasia())
                .nomeSocial(organizacaoDTO.getNomeSocial())
                .cnpj(organizacaoDTO.getCnpj())
                .dataFundacao(organizacaoDTO.getDataFundacao())
                .build();

        organizacaoRepository.save(organizacaoAtualizada);
        organizacaoDTO = OrganizacaoMapper.paraDTO(organizacaoAtualizada);
        return ResponseEntity.ok().body(organizacaoDTO);
    }

    public ResponseEntity<Void> deletarOrganizacao(UUID id) {
        if (!organizacaoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        organizacaoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

